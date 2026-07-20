package net.multigesture.kanama.processor

// Serializes the platform-neutral [ScriptModel] to JSON — the Phase 3.1 artifact the iOS
// build consumes instead of regex-parsing the source (script-model-unification-design.md).
// Hand-rolled (zero deps) so the model types stay free of any serialization-lib coupling.
//
// The JSON is a faithful, lossless dump of the data classes: a TypeMapping is emitted by
// its enum name (a neutral type descriptor); each platform's emitter re-derives marshalling
// from that name. SCHEMA_VERSION is bumped whenever the shape changes; the consumer
// fails closed on a mismatch.

internal const val SCRIPT_MODEL_SCHEMA_VERSION = 6

internal fun scriptModelToJson(model: ScriptModel): String {
    val sb = StringBuilder()
    JsonWriter(sb).obj {
        field("schemaVersion", SCRIPT_MODEL_SCHEMA_VERSION)
        field("simpleName", model.simpleName)
        field("fqName", model.fqName)
        field("attachTo", model.attachTo)
        field("isTool", model.isTool)
        field("isGlobalClass", model.isGlobalClass)
        arrayField("properties", model.properties) { writeProperty(it) }
        arrayField("methods", model.methods) { writeMethod(it) }
        arrayField("virtuals", model.virtuals) { writeVirtual(it) }
        arrayField("signals", model.signals) { writeSignal(it) }
        arrayField("toolButtons", model.toolButtons) { writeToolButton(it) }
    }
    return sb.toString()
}

private fun JsonWriter.writeProperty(p: ScriptPropertyModel) = obj {
    field("kotlinName", p.kotlinName)
    field("godotName", p.godotName)
    field("type", p.type.name)
    field("isMutable", p.isMutable)
    field("nullable", p.nullable)
    field("hint", p.hint)
    field("hintString", p.hintString)
    field("usage", p.usage)
    nullableField("defaultLiteral", p.defaultLiteral)
    nullableField("objectWrapperFqName", p.objectWrapperFqName)
    nullableField("arrayElementWrapperFqName", p.arrayElementWrapperFqName)
    nullableField("customScriptFqName", p.customScriptFqName)
    nullableField("arrayElementCustomScriptFqName", p.arrayElementCustomScriptFqName)
    field("customScriptIsResource", p.customScriptIsResource)
    field("arrayElementCustomScriptIsResource", p.arrayElementCustomScriptIsResource)
    field("arrayElementString", p.arrayElementString)
    nullableField("enumFqName", p.enumFqName)
    stringArrayField("enumEntries", p.enumEntries)
    nullableField("arrayElementEnumFqName", p.arrayElementEnumFqName)
    stringArrayField("arrayElementEnumEntries", p.arrayElementEnumEntries)
    nullableField("mapKeyKotlinType", p.mapKeyKotlinType)
    stringArrayField("mapKeyEnumEntries", p.mapKeyEnumEntries)
    nullableField("mapValueKotlinType", p.mapValueKotlinType)
    nullableField("mapValueWrapperFqName", p.mapValueWrapperFqName)
    nullableField("mapValueCustomScriptFqName", p.mapValueCustomScriptFqName)
    field("mapValueCustomScriptIsResource", p.mapValueCustomScriptIsResource)
    nullableField("mapValueEnumFqName", p.mapValueEnumFqName)
    stringArrayField("mapValueEnumEntries", p.mapValueEnumEntries)
    field("mapValueNullable", p.mapValueNullable)
    field("isMutableMap", p.isMutableMap)
    field("isMutableList", p.isMutableList)
    objectOrNullField("exportCategory", p.exportCategory) { writeGroup(it) }
    objectOrNullField("exportGroup", p.exportGroup) { writeGroup(it) }
    objectOrNullField("exportSubgroup", p.exportSubgroup) { writeGroup(it) }
}

private fun JsonWriter.writeGroup(g: ScriptPropertyGroupModel) = obj {
    field("name", g.name)
    field("prefix", g.prefix)
    field("usage", g.usage)
}

private fun JsonWriter.writeMethod(m: MethodModel) = obj {
    field("kotlinName", m.kotlinName)
    field("godotName", m.godotName)
    field("kind", m.kind.name)
    nullableField("returnType", m.returnType?.name)
    nullableField("propertyKotlinName", m.propertyKotlinName)
    arrayField("args", m.args) { writeArg(it) }
    objectOrNullField("rpc", m.rpc) { writeRpc(it) }
}

private fun JsonWriter.writeArg(a: ArgModel) = obj {
    field("name", a.name)
    field("type", a.type.name)
    nullableField("objectWrapperFqName", a.objectWrapperFqName)
    field("nullable", a.nullable)
    field("hasDefault", a.hasDefault)
}

private fun JsonWriter.writeRpc(r: RpcModel) = obj {
    field("mode", r.mode)
    field("callLocal", r.callLocal)
    field("transferMode", r.transferMode)
    field("channel", r.channel)
}

private fun JsonWriter.writeVirtual(v: VirtualModel) = obj {
    field("virtualName", v.virtualName)
    field("kotlinMethodName", v.kotlinMethodName)
    arrayField("args", v.args) { writeArg(it) }
}

private fun JsonWriter.writeSignal(s: SignalModel) = obj {
    field("godotName", s.godotName)
    arrayField("args", s.args) { writeArg(it) }
}

private fun JsonWriter.writeToolButton(t: ToolButtonModel) = obj {
    field("propertyName", t.propertyName)
    field("text", t.text)
    field("icon", t.icon)
    field("methodGodotName", t.method.godotName)
}

// ---------- minimal JSON writer (objects, arrays, string/int/bool scalars) ----------

internal class JsonWriter(private val sb: StringBuilder) {
    private var needComma = false

    fun obj(body: JsonWriter.() -> Unit) {
        sb.append('{')
        val saved = needComma
        needComma = false
        body()
        needComma = saved
        sb.append('}')
        needComma = true
    }

    fun field(name: String, value: String) = scalarField(name) { writeString(value) }
    fun field(name: String, value: Int) = scalarField(name) { sb.append(value) }
    fun field(name: String, value: Boolean) = scalarField(name) { sb.append(value) }

    fun nullableField(name: String, value: String?) =
        scalarField(name) { if (value == null) sb.append("null") else writeString(value) }

    fun <T> arrayField(name: String, items: List<T>, write: JsonWriter.(T) -> Unit) {
        key(name)
        sb.append('[')
        val saved = needComma
        needComma = false
        items.forEach { item ->
            if (needComma) sb.append(',')
            needComma = false
            write(item)
            needComma = true
        }
        needComma = saved
        sb.append(']')
        needComma = true
    }

    fun stringArrayField(name: String, items: List<String>) {
        key(name)
        sb.append('[')
        items.forEachIndexed { index, item ->
            if (index > 0) sb.append(',')
            writeString(item)
        }
        sb.append(']')
        needComma = true
    }

    fun <T> objectOrNullField(name: String, value: T?, write: JsonWriter.(T) -> Unit) {
        key(name)
        if (value == null) {
            sb.append("null")
            needComma = true
        } else {
            write(value)
        }
    }

    private inline fun scalarField(name: String, writeValue: () -> Unit) {
        key(name)
        writeValue()
        needComma = true
    }

    private fun key(name: String) {
        if (needComma) sb.append(',')
        writeString(name)
        sb.append(':')
        needComma = false
    }

    private fun writeString(value: String) {
        sb.append('"')
        value.forEach { ch ->
            when (ch) {
                '\\' -> sb.append("\\\\")
                '"' -> sb.append("\\\"")
                '\n' -> sb.append("\\n")
                '\r' -> sb.append("\\r")
                '\t' -> sb.append("\\t")
                else -> if (ch < ' ') sb.append("\\u%04x".format(ch.code)) else sb.append(ch)
            }
        }
        sb.append('"')
    }
}

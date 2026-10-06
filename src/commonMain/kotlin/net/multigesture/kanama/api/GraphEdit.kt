package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * An editor for graph-like structures, using `GraphNode`s.
 *
 * Generated from Godot docs: GraphEdit
 */
class GraphEdit(handle: GodotHandle) : Control(handle) {
    var scrollOffset: Vector2
        @JvmName("scrollOffsetProperty")
        get() = getScrollOffset()
        @JvmName("setScrollOffsetProperty")
        set(value) = setScrollOffset(value)

    var showGrid: Boolean
        @JvmName("showGridProperty")
        get() = isShowingGrid()
        @JvmName("setShowGridProperty")
        set(value) = setShowGrid(value)

    var gridPattern: GraphEdit.GridPattern
        @JvmName("gridPatternProperty")
        get() = getGridPattern()
        @JvmName("setGridPatternProperty")
        set(value) = setGridPattern(value)

    var snappingEnabled: Boolean
        @JvmName("snappingEnabledProperty")
        get() = isSnappingEnabled()
        @JvmName("setSnappingEnabledProperty")
        set(value) = setSnappingEnabled(value)

    var snappingDistance: Int
        @JvmName("snappingDistanceProperty")
        get() = getSnappingDistance()
        @JvmName("setSnappingDistanceProperty")
        set(value) = setSnappingDistance(value)

    var panningScheme: GraphEdit.PanningScheme
        @JvmName("panningSchemeProperty")
        get() = getPanningScheme()
        @JvmName("setPanningSchemeProperty")
        set(value) = setPanningScheme(value)

    var rightDisconnects: Boolean
        @JvmName("rightDisconnectsProperty")
        get() = isRightDisconnectsEnabled()
        @JvmName("setRightDisconnectsProperty")
        set(value) = setRightDisconnects(value)

    var typeNames: Map<String, Any?>
        @JvmName("typeNamesProperty")
        get() = getTypeNames()
        @JvmName("setTypeNamesProperty")
        set(value) = setTypeNames(value)

    var connectionLinesCurvature: Double
        @JvmName("connectionLinesCurvatureProperty")
        get() = getConnectionLinesCurvature()
        @JvmName("setConnectionLinesCurvatureProperty")
        set(value) = setConnectionLinesCurvature(value)

    var connectionLinesThickness: Double
        @JvmName("connectionLinesThicknessProperty")
        get() = getConnectionLinesThickness()
        @JvmName("setConnectionLinesThicknessProperty")
        set(value) = setConnectionLinesThickness(value)

    var connectionLinesAntialiased: Boolean
        @JvmName("connectionLinesAntialiasedProperty")
        get() = isConnectionLinesAntialiased()
        @JvmName("setConnectionLinesAntialiasedProperty")
        set(value) = setConnectionLinesAntialiased(value)

    var connections: List<Map<String, Any?>>
        @JvmName("connectionsProperty")
        get() = getConnectionList()
        @JvmName("setConnectionsProperty")
        set(value) = setConnections(value)

    var zoom: Double
        @JvmName("zoomProperty")
        get() = getZoom()
        @JvmName("setZoomProperty")
        set(value) = setZoom(value)

    var zoomMin: Double
        @JvmName("zoomMinProperty")
        get() = getZoomMin()
        @JvmName("setZoomMinProperty")
        set(value) = setZoomMin(value)

    var zoomMax: Double
        @JvmName("zoomMaxProperty")
        get() = getZoomMax()
        @JvmName("setZoomMaxProperty")
        set(value) = setZoomMax(value)

    var zoomStep: Double
        @JvmName("zoomStepProperty")
        get() = getZoomStep()
        @JvmName("setZoomStepProperty")
        set(value) = setZoomStep(value)

    var minimapEnabled: Boolean
        @JvmName("minimapEnabledProperty")
        get() = isMinimapEnabled()
        @JvmName("setMinimapEnabledProperty")
        set(value) = setMinimapEnabled(value)

    var minimapSize: Vector2
        @JvmName("minimapSizeProperty")
        get() = getMinimapSize()
        @JvmName("setMinimapSizeProperty")
        set(value) = setMinimapSize(value)

    var minimapOpacity: Double
        @JvmName("minimapOpacityProperty")
        get() = getMinimapOpacity()
        @JvmName("setMinimapOpacityProperty")
        set(value) = setMinimapOpacity(value)

    var showMenu: Boolean
        @JvmName("showMenuProperty")
        get() = isShowingMenu()
        @JvmName("setShowMenuProperty")
        set(value) = setShowMenu(value)

    var showZoomLabel: Boolean
        @JvmName("showZoomLabelProperty")
        get() = isShowingZoomLabel()
        @JvmName("setShowZoomLabelProperty")
        set(value) = setShowZoomLabel(value)

    var showZoomButtons: Boolean
        @JvmName("showZoomButtonsProperty")
        get() = isShowingZoomButtons()
        @JvmName("setShowZoomButtonsProperty")
        set(value) = setShowZoomButtons(value)

    var showGridButtons: Boolean
        @JvmName("showGridButtonsProperty")
        get() = isShowingGridButtons()
        @JvmName("setShowGridButtonsProperty")
        set(value) = setShowGridButtons(value)

    var showMinimapButton: Boolean
        @JvmName("showMinimapButtonProperty")
        get() = isShowingMinimapButton()
        @JvmName("setShowMinimapButtonProperty")
        set(value) = setShowMinimapButton(value)

    var showArrangeButton: Boolean
        @JvmName("showArrangeButtonProperty")
        get() = isShowingArrangeButton()
        @JvmName("setShowArrangeButtonProperty")
        set(value) = setShowArrangeButton(value)

    /**
     * Create a connection between the `from_port` of the `from_node` `GraphNode` and the `to_port` of
     * the `to_node` `GraphNode`. If the connection already exists, no connection is created.
     * Connections with `keep_alive` set to `false` may be deleted automatically if invalid during a
     * redraw.
     *
     * Generated from Godot docs: GraphEdit.connect_node
     */
    fun connectNode(fromNode: String, fromPort: Int, toNode: String, toPort: Int, keepAlive: Boolean = false): GodotError {
        return GodotError(ObjectCalls.ptrcallWithStringNameIntStringNameIntBoolArgsRetLong(Binds.connectNodeBind, segment, fromNode, fromPort, toNode, toPort, keepAlive))
    }

    /**
     * Returns `true` if the `from_port` of the `from_node` `GraphNode` is connected to the `to_port`
     * of the `to_node` `GraphNode`.
     *
     * Generated from Godot docs: GraphEdit.is_node_connected
     */
    fun isNodeConnected(fromNode: String, fromPort: Int, toNode: String, toPort: Int): Boolean {
        return ObjectCalls.ptrcallWithStringNameIntStringNameIntArgsRetBool(Binds.isNodeConnectedBind, segment, fromNode, fromPort, toNode, toPort)
    }

    /**
     * Removes the connection between the `from_port` of the `from_node` `GraphNode` and the `to_port`
     * of the `to_node` `GraphNode`. If the connection does not exist, no connection is removed.
     *
     * Generated from Godot docs: GraphEdit.disconnect_node
     */
    fun disconnectNode(fromNode: String, fromPort: Int, toNode: String, toPort: Int) {
        ObjectCalls.ptrcallWithStringNameIntStringNameIntArgs(Binds.disconnectNodeBind, segment, fromNode, fromPort, toNode, toPort)
    }

    /**
     * Sets the coloration of the connection between `from_node`'s `from_port` and `to_node`'s
     * `to_port` with the color provided in the `activity` theme property. The color is linearly
     * interpolated between the connection color and the activity color using `amount` as weight.
     *
     * Generated from Godot docs: GraphEdit.set_connection_activity
     */
    fun setConnectionActivity(fromNode: String, fromPort: Int, toNode: String, toPort: Int, amount: Double) {
        ObjectCalls.ptrcallWithStringNameIntStringNameIntDoubleArgs(Binds.setConnectionActivityBind, segment, fromNode, fromPort, toNode, toPort, amount)
    }

    /**
     * The connections between `GraphNode`s.
     *
     * Generated from Godot docs: GraphEdit.set_connections
     */
    fun setConnections(connections: List<Map<String, Any?>>) {
        ObjectCalls.ptrcallWithDictionaryListArg(Binds.setConnectionsBind, segment, connections)
    }

    /**
     * The connections between `GraphNode`s.
     *
     * Generated from Godot docs: GraphEdit.get_connection_list
     */
    fun getConnectionList(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(Binds.getConnectionListBind, segment)
    }

    /**
     * Returns the number of connections from `from_port` of `from_node`.
     *
     * Generated from Godot docs: GraphEdit.get_connection_count
     */
    fun getConnectionCount(fromNode: String, fromPort: Int): Int {
        return ObjectCalls.ptrcallWithStringNameAndIntArgRetInt(Binds.getConnectionCountBind, segment, fromNode, fromPort)
    }

    /**
     * Returns the closest connection to the given point in screen space. If no connection is found
     * within `max_distance` pixels, an empty `Dictionary` is returned.
     *
     * Generated from Godot docs: GraphEdit.get_closest_connection_at_point
     */
    fun getClosestConnectionAtPoint(point: Vector2, maxDistance: Double = 4.0): Map<String, Any?> {
        return ObjectCalls.ptrcallWithVector2AndDoubleArgRetDictionary(Binds.getClosestConnectionAtPointBind, segment, point, maxDistance)
    }

    /**
     * Returns an `Array` containing a list of all connections for `node`.
     *
     * Generated from Godot docs: GraphEdit.get_connection_list_from_node
     */
    fun getConnectionListFromNode(node: String): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithStringNameArgRetDictionaryList(Binds.getConnectionListFromNodeBind, segment, node)
    }

    /**
     * Returns an `Array` containing the list of connections that intersect with the given `Rect2`.
     *
     * Generated from Godot docs: GraphEdit.get_connections_intersecting_with_rect
     */
    fun getConnectionsIntersectingWithRect(rect: Rect2): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithRect2ArgRetDictionaryList(Binds.getConnectionsIntersectingWithRectBind, segment, rect)
    }

    /**
     * Removes all connections between nodes.
     *
     * Generated from Godot docs: GraphEdit.clear_connections
     */
    fun clearConnections() {
        ObjectCalls.ptrcallNoArgs(Binds.clearConnectionsBind, segment)
    }

    /**
     * Ends the creation of the current connection. In other words, if you are dragging a connection
     * you can use this method to abort the process and remove the line that followed your cursor. This
     * is best used together with `connection_drag_started` and `connection_drag_ended` to add custom
     * behavior like node addition through shortcuts. Note: This method suppresses any other connection
     * request signals apart from `connection_drag_ended`.
     *
     * Generated from Godot docs: GraphEdit.force_connection_drag_end
     */
    fun forceConnectionDragEnd() {
        ObjectCalls.ptrcallNoArgs(Binds.forceConnectionDragEndBind, segment)
    }

    /**
     * The scroll offset.
     *
     * Generated from Godot docs: GraphEdit.get_scroll_offset
     */
    fun getScrollOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getScrollOffsetBind, segment)
    }

    /**
     * The scroll offset.
     *
     * Generated from Godot docs: GraphEdit.set_scroll_offset
     */
    fun setScrollOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setScrollOffsetBind, segment, offset)
    }

    /**
     * Allows to disconnect nodes when dragging from the right port of the `GraphNode`'s slot if it has
     * the specified type. See also `remove_valid_right_disconnect_type`.
     *
     * Generated from Godot docs: GraphEdit.add_valid_right_disconnect_type
     */
    fun addValidRightDisconnectType(type: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.addValidRightDisconnectTypeBind, segment, type)
    }

    /**
     * Disallows to disconnect nodes when dragging from the right port of the `GraphNode`'s slot if it
     * has the specified type. Use this to disable a disconnection previously allowed with
     * `add_valid_right_disconnect_type`.
     *
     * Generated from Godot docs: GraphEdit.remove_valid_right_disconnect_type
     */
    fun removeValidRightDisconnectType(type: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.removeValidRightDisconnectTypeBind, segment, type)
    }

    /**
     * Allows to disconnect nodes when dragging from the left port of the `GraphNode`'s slot if it has
     * the specified type. See also `remove_valid_left_disconnect_type`.
     *
     * Generated from Godot docs: GraphEdit.add_valid_left_disconnect_type
     */
    fun addValidLeftDisconnectType(type: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.addValidLeftDisconnectTypeBind, segment, type)
    }

    /**
     * Disallows to disconnect nodes when dragging from the left port of the `GraphNode`'s slot if it
     * has the specified type. Use this to disable a disconnection previously allowed with
     * `add_valid_left_disconnect_type`.
     *
     * Generated from Godot docs: GraphEdit.remove_valid_left_disconnect_type
     */
    fun removeValidLeftDisconnectType(type: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.removeValidLeftDisconnectTypeBind, segment, type)
    }

    /**
     * Allows the connection between two different port types. The port type is defined individually
     * for the left and the right port of each slot with the `GraphNode.set_slot` method. See also
     * `is_valid_connection_type` and `remove_valid_connection_type`.
     *
     * Generated from Godot docs: GraphEdit.add_valid_connection_type
     */
    fun addValidConnectionType(fromType: Int, toType: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.addValidConnectionTypeBind, segment, fromType, toType)
    }

    /**
     * Disallows the connection between two different port types previously allowed by
     * `add_valid_connection_type`. The port type is defined individually for the left and the right
     * port of each slot with the `GraphNode.set_slot` method. See also `is_valid_connection_type`.
     *
     * Generated from Godot docs: GraphEdit.remove_valid_connection_type
     */
    fun removeValidConnectionType(fromType: Int, toType: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.removeValidConnectionTypeBind, segment, fromType, toType)
    }

    /**
     * Returns whether it's possible to make a connection between two different port types. The port
     * type is defined individually for the left and the right port of each slot with the
     * `GraphNode.set_slot` method. See also `add_valid_connection_type` and
     * `remove_valid_connection_type`.
     *
     * Generated from Godot docs: GraphEdit.is_valid_connection_type
     */
    fun isValidConnectionType(fromType: Int, toType: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(Binds.isValidConnectionTypeBind, segment, fromType, toType)
    }

    /**
     * Returns the points which would make up a connection between `from_node` and `to_node`.
     *
     * Generated from Godot docs: GraphEdit.get_connection_line
     */
    fun getConnectionLine(fromNode: Vector2, toNode: Vector2): List<Vector2> {
        return ObjectCalls.ptrcallWithTwoVector2ArgsRetPackedVector2List(Binds.getConnectionLineBind, segment, fromNode, toNode)
    }

    /**
     * Attaches the `element` `GraphElement` to the `frame` `GraphFrame`.
     *
     * Generated from Godot docs: GraphEdit.attach_graph_element_to_frame
     */
    fun attachGraphElementToFrame(element: String, frame: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(Binds.attachGraphElementToFrameBind, segment, element, frame)
    }

    /**
     * Detaches the `element` `GraphElement` from the `GraphFrame` it is currently attached to.
     *
     * Generated from Godot docs: GraphEdit.detach_graph_element_from_frame
     */
    fun detachGraphElementFromFrame(element: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.detachGraphElementFromFrameBind, segment, element)
    }

    /**
     * Returns the `GraphFrame` that contains the `GraphElement` with the given name.
     *
     * Generated from Godot docs: GraphEdit.get_element_frame
     */
    fun getElementFrame(element: String): GraphFrame? {
        return GraphFrame.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(Binds.getElementFrameBind, segment, element))
    }

    /**
     * Returns an array of node names that are attached to the `GraphFrame` with the given name.
     *
     * Generated from Godot docs: GraphEdit.get_attached_nodes_of_frame
     */
    fun getAttachedNodesOfFrame(frame: String): List<String> {
        return ObjectCalls.ptrcallWithStringNameArgRetStringNameList(Binds.getAttachedNodesOfFrameBind, segment, frame)
    }

    /**
     * Defines the control scheme for panning with mouse wheel.
     *
     * Generated from Godot docs: GraphEdit.set_panning_scheme
     */
    fun setPanningScheme(scheme: GraphEdit.PanningScheme) {
        ObjectCalls.ptrcallWithLongArg(Binds.setPanningSchemeBind, segment, scheme.value)
    }

    /**
     * Defines the control scheme for panning with mouse wheel.
     *
     * Generated from Godot docs: GraphEdit.get_panning_scheme
     */
    fun getPanningScheme(): GraphEdit.PanningScheme {
        return GraphEdit.PanningScheme(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPanningSchemeBind, segment))
    }

    /**
     * The current zoom value.
     *
     * Generated from Godot docs: GraphEdit.set_zoom
     */
    fun setZoom(zoom: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setZoomBind, segment, zoom)
    }

    /**
     * The current zoom value.
     *
     * Generated from Godot docs: GraphEdit.get_zoom
     */
    fun getZoom(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getZoomBind, segment)
    }

    /**
     * The lower zoom limit.
     *
     * Generated from Godot docs: GraphEdit.set_zoom_min
     */
    fun setZoomMin(zoomMin: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setZoomMinBind, segment, zoomMin)
    }

    /**
     * The lower zoom limit.
     *
     * Generated from Godot docs: GraphEdit.get_zoom_min
     */
    fun getZoomMin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getZoomMinBind, segment)
    }

    /**
     * The upper zoom limit.
     *
     * Generated from Godot docs: GraphEdit.set_zoom_max
     */
    fun setZoomMax(zoomMax: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setZoomMaxBind, segment, zoomMax)
    }

    /**
     * The upper zoom limit.
     *
     * Generated from Godot docs: GraphEdit.get_zoom_max
     */
    fun getZoomMax(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getZoomMaxBind, segment)
    }

    /**
     * The step of each zoom level.
     *
     * Generated from Godot docs: GraphEdit.set_zoom_step
     */
    fun setZoomStep(zoomStep: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setZoomStepBind, segment, zoomStep)
    }

    /**
     * The step of each zoom level.
     *
     * Generated from Godot docs: GraphEdit.get_zoom_step
     */
    fun getZoomStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getZoomStepBind, segment)
    }

    /**
     * If `true`, the grid is visible.
     *
     * Generated from Godot docs: GraphEdit.set_show_grid
     */
    fun setShowGrid(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShowGridBind, segment, enable)
    }

    /**
     * If `true`, the grid is visible.
     *
     * Generated from Godot docs: GraphEdit.is_showing_grid
     */
    fun isShowingGrid(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isShowingGridBind, segment)
    }

    /**
     * The pattern used for drawing the grid.
     *
     * Generated from Godot docs: GraphEdit.set_grid_pattern
     */
    fun setGridPattern(pattern: GraphEdit.GridPattern) {
        ObjectCalls.ptrcallWithLongArg(Binds.setGridPatternBind, segment, pattern.value)
    }

    /**
     * The pattern used for drawing the grid.
     *
     * Generated from Godot docs: GraphEdit.get_grid_pattern
     */
    fun getGridPattern(): GraphEdit.GridPattern {
        return GraphEdit.GridPattern(ObjectCalls.ptrcallNoArgsRetLong(Binds.getGridPatternBind, segment))
    }

    /**
     * If `true`, enables snapping.
     *
     * Generated from Godot docs: GraphEdit.set_snapping_enabled
     */
    fun setSnappingEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSnappingEnabledBind, segment, enable)
    }

    /**
     * If `true`, enables snapping.
     *
     * Generated from Godot docs: GraphEdit.is_snapping_enabled
     */
    fun isSnappingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSnappingEnabledBind, segment)
    }

    /**
     * The snapping distance in pixels, also determines the grid line distance.
     *
     * Generated from Godot docs: GraphEdit.set_snapping_distance
     */
    fun setSnappingDistance(pixels: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSnappingDistanceBind, segment, pixels)
    }

    /**
     * The snapping distance in pixels, also determines the grid line distance.
     *
     * Generated from Godot docs: GraphEdit.get_snapping_distance
     */
    fun getSnappingDistance(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSnappingDistanceBind, segment)
    }

    /**
     * The curvature of the lines between the nodes. 0 results in straight lines.
     *
     * Generated from Godot docs: GraphEdit.set_connection_lines_curvature
     */
    fun setConnectionLinesCurvature(curvature: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setConnectionLinesCurvatureBind, segment, curvature)
    }

    /**
     * The curvature of the lines between the nodes. 0 results in straight lines.
     *
     * Generated from Godot docs: GraphEdit.get_connection_lines_curvature
     */
    fun getConnectionLinesCurvature(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getConnectionLinesCurvatureBind, segment)
    }

    /**
     * The thickness of the lines between the nodes.
     *
     * Generated from Godot docs: GraphEdit.set_connection_lines_thickness
     */
    fun setConnectionLinesThickness(pixels: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setConnectionLinesThicknessBind, segment, pixels)
    }

    /**
     * The thickness of the lines between the nodes.
     *
     * Generated from Godot docs: GraphEdit.get_connection_lines_thickness
     */
    fun getConnectionLinesThickness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getConnectionLinesThicknessBind, segment)
    }

    /**
     * If `true`, the lines between nodes will use antialiasing.
     *
     * Generated from Godot docs: GraphEdit.set_connection_lines_antialiased
     */
    fun setConnectionLinesAntialiased(pixels: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setConnectionLinesAntialiasedBind, segment, pixels)
    }

    /**
     * If `true`, the lines between nodes will use antialiasing.
     *
     * Generated from Godot docs: GraphEdit.is_connection_lines_antialiased
     */
    fun isConnectionLinesAntialiased(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isConnectionLinesAntialiasedBind, segment)
    }

    /**
     * The size of the minimap rectangle. The map itself is based on the size of the grid area and is
     * scaled to fit this rectangle.
     *
     * Generated from Godot docs: GraphEdit.set_minimap_size
     */
    fun setMinimapSize(size: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setMinimapSizeBind, segment, size)
    }

    /**
     * The size of the minimap rectangle. The map itself is based on the size of the grid area and is
     * scaled to fit this rectangle.
     *
     * Generated from Godot docs: GraphEdit.get_minimap_size
     */
    fun getMinimapSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getMinimapSizeBind, segment)
    }

    /**
     * The opacity of the minimap rectangle.
     *
     * Generated from Godot docs: GraphEdit.set_minimap_opacity
     */
    fun setMinimapOpacity(opacity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMinimapOpacityBind, segment, opacity)
    }

    /**
     * The opacity of the minimap rectangle.
     *
     * Generated from Godot docs: GraphEdit.get_minimap_opacity
     */
    fun getMinimapOpacity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMinimapOpacityBind, segment)
    }

    /**
     * If `true`, the minimap is visible.
     *
     * Generated from Godot docs: GraphEdit.set_minimap_enabled
     */
    fun setMinimapEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setMinimapEnabledBind, segment, enable)
    }

    /**
     * If `true`, the minimap is visible.
     *
     * Generated from Godot docs: GraphEdit.is_minimap_enabled
     */
    fun isMinimapEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMinimapEnabledBind, segment)
    }

    /**
     * If `true`, the menu toolbar is visible.
     *
     * Generated from Godot docs: GraphEdit.set_show_menu
     */
    fun setShowMenu(hidden: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShowMenuBind, segment, hidden)
    }

    /**
     * If `true`, the menu toolbar is visible.
     *
     * Generated from Godot docs: GraphEdit.is_showing_menu
     */
    fun isShowingMenu(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isShowingMenuBind, segment)
    }

    /**
     * If `true`, the label with the current zoom level is visible. The zoom level is displayed in
     * percents.
     *
     * Generated from Godot docs: GraphEdit.set_show_zoom_label
     */
    fun setShowZoomLabel(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShowZoomLabelBind, segment, enable)
    }

    /**
     * If `true`, the label with the current zoom level is visible. The zoom level is displayed in
     * percents.
     *
     * Generated from Godot docs: GraphEdit.is_showing_zoom_label
     */
    fun isShowingZoomLabel(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isShowingZoomLabelBind, segment)
    }

    /**
     * If `true`, buttons that allow to configure grid and snapping options are visible.
     *
     * Generated from Godot docs: GraphEdit.set_show_grid_buttons
     */
    fun setShowGridButtons(hidden: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShowGridButtonsBind, segment, hidden)
    }

    /**
     * If `true`, buttons that allow to configure grid and snapping options are visible.
     *
     * Generated from Godot docs: GraphEdit.is_showing_grid_buttons
     */
    fun isShowingGridButtons(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isShowingGridButtonsBind, segment)
    }

    /**
     * If `true`, buttons that allow to change and reset the zoom level are visible.
     *
     * Generated from Godot docs: GraphEdit.set_show_zoom_buttons
     */
    fun setShowZoomButtons(hidden: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShowZoomButtonsBind, segment, hidden)
    }

    /**
     * If `true`, buttons that allow to change and reset the zoom level are visible.
     *
     * Generated from Godot docs: GraphEdit.is_showing_zoom_buttons
     */
    fun isShowingZoomButtons(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isShowingZoomButtonsBind, segment)
    }

    /**
     * If `true`, the button to toggle the minimap is visible.
     *
     * Generated from Godot docs: GraphEdit.set_show_minimap_button
     */
    fun setShowMinimapButton(hidden: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShowMinimapButtonBind, segment, hidden)
    }

    /**
     * If `true`, the button to toggle the minimap is visible.
     *
     * Generated from Godot docs: GraphEdit.is_showing_minimap_button
     */
    fun isShowingMinimapButton(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isShowingMinimapButtonBind, segment)
    }

    /**
     * If `true`, the button to automatically arrange graph nodes is visible.
     *
     * Generated from Godot docs: GraphEdit.set_show_arrange_button
     */
    fun setShowArrangeButton(hidden: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShowArrangeButtonBind, segment, hidden)
    }

    /**
     * If `true`, the button to automatically arrange graph nodes is visible.
     *
     * Generated from Godot docs: GraphEdit.is_showing_arrange_button
     */
    fun isShowingArrangeButton(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isShowingArrangeButtonBind, segment)
    }

    /**
     * If `true`, enables disconnection of existing connections in the GraphEdit by dragging the right
     * end.
     *
     * Generated from Godot docs: GraphEdit.set_right_disconnects
     */
    fun setRightDisconnects(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setRightDisconnectsBind, segment, enable)
    }

    /**
     * If `true`, enables disconnection of existing connections in the GraphEdit by dragging the right
     * end.
     *
     * Generated from Godot docs: GraphEdit.is_right_disconnects_enabled
     */
    fun isRightDisconnectsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isRightDisconnectsEnabledBind, segment)
    }

    /**
     * `Dictionary` of human-readable port type names.
     *
     * Generated from Godot docs: GraphEdit.set_type_names
     */
    fun setTypeNames(typeNames: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(Binds.setTypeNamesBind, segment, typeNames)
    }

    /**
     * `Dictionary` of human-readable port type names.
     *
     * Generated from Godot docs: GraphEdit.get_type_names
     */
    fun getTypeNames(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getTypeNamesBind, segment)
    }

    /**
     * Gets the `HBoxContainer` that contains the zooming and grid snap controls in the top left of the
     * graph. You can use this method to reposition the toolbar or to add your own custom controls to
     * it. Warning: This is a required internal node, removing and freeing it may cause a crash. If you
     * wish to hide it or any of its children, use their `CanvasItem.visible` property.
     *
     * Generated from Godot docs: GraphEdit.get_menu_hbox
     */
    fun getMenuHbox(): HBoxContainer? {
        return HBoxContainer.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMenuHboxBind, segment))
    }

    /**
     * Rearranges selected nodes in a layout with minimum crossings between connections and uniform
     * horizontal and vertical gap between nodes.
     *
     * Generated from Godot docs: GraphEdit.arrange_nodes
     */
    fun arrangeNodes() {
        ObjectCalls.ptrcallNoArgs(Binds.arrangeNodesBind, segment)
    }

    /**
     * Sets the specified `node` as the one selected.
     *
     * Generated from Godot docs: GraphEdit.set_selected
     */
    fun setSelected(node: Node) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setSelectedBind, segment, listOf(node.segment))
    }

    /** Signal `connection_request(from_node: StringName, from_port: int, to_node: StringName, to_port: int)`; see [TypedSignal]. */
    val connectionRequest: Signal4<String, Long, String, Long>
        @JvmName("connectionRequestTypedSignal")
        get() = Signal4(this, "connection_request", SignalArgType.STRING, SignalArgType.LONG, SignalArgType.STRING, SignalArgType.LONG)

    /** Signal `disconnection_request(from_node: StringName, from_port: int, to_node: StringName, to_port: int)`; see [TypedSignal]. */
    val disconnectionRequest: Signal4<String, Long, String, Long>
        @JvmName("disconnectionRequestTypedSignal")
        get() = Signal4(this, "disconnection_request", SignalArgType.STRING, SignalArgType.LONG, SignalArgType.STRING, SignalArgType.LONG)

    /** Signal `connection_to_empty(from_node: StringName, from_port: int, release_position: Vector2)`; see [TypedSignal]. */
    val connectionToEmpty: Signal3<String, Long, Vector2>
        @JvmName("connectionToEmptyTypedSignal")
        get() = Signal3(this, "connection_to_empty", SignalArgType.STRING, SignalArgType.LONG, SignalArgType.valueOf<Vector2>("Vector2", Vector2::class))

    /** Signal `connection_from_empty(to_node: StringName, to_port: int, release_position: Vector2)`; see [TypedSignal]. */
    val connectionFromEmpty: Signal3<String, Long, Vector2>
        @JvmName("connectionFromEmptyTypedSignal")
        get() = Signal3(this, "connection_from_empty", SignalArgType.STRING, SignalArgType.LONG, SignalArgType.valueOf<Vector2>("Vector2", Vector2::class))

    /** Signal `connection_drag_started(from_node: StringName, from_port: int, is_output: bool)`; see [TypedSignal]. */
    val connectionDragStarted: Signal3<String, Long, Boolean>
        @JvmName("connectionDragStartedTypedSignal")
        get() = Signal3(this, "connection_drag_started", SignalArgType.STRING, SignalArgType.LONG, SignalArgType.BOOLEAN)

    /** Signal `connection_drag_ended()`; see [TypedSignal]. */
    val connectionDragEnded: Signal0
        @JvmName("connectionDragEndedTypedSignal")
        get() = Signal0(this, "connection_drag_ended")

    /** Signal `copy_nodes_request()`; see [TypedSignal]. */
    val copyNodesRequest: Signal0
        @JvmName("copyNodesRequestTypedSignal")
        get() = Signal0(this, "copy_nodes_request")

    /** Signal `cut_nodes_request()`; see [TypedSignal]. */
    val cutNodesRequest: Signal0
        @JvmName("cutNodesRequestTypedSignal")
        get() = Signal0(this, "cut_nodes_request")

    /** Signal `paste_nodes_request()`; see [TypedSignal]. */
    val pasteNodesRequest: Signal0
        @JvmName("pasteNodesRequestTypedSignal")
        get() = Signal0(this, "paste_nodes_request")

    /** Signal `duplicate_nodes_request()`; see [TypedSignal]. */
    val duplicateNodesRequest: Signal0
        @JvmName("duplicateNodesRequestTypedSignal")
        get() = Signal0(this, "duplicate_nodes_request")

    /** Signal `delete_nodes_request(nodes: typedarray::StringName)`; see [TypedSignal]. On iOS a typedarray::StringName argument is not delivered yet: a connection reports a script error. */
    val deleteNodesRequest: Signal1<List<String>>
        @JvmName("deleteNodesRequestTypedSignal")
        get() = Signal1(this, "delete_nodes_request", SignalArgType.valueOf<List<String>>("typedarray::StringName", List::class))

    /** Signal `node_selected(node: Node)`; see [TypedSignal]. */
    val nodeSelected: Signal1<Node>
        @JvmName("nodeSelectedTypedSignal")
        get() = Signal1(this, "node_selected", SignalArgType.objectOf("Node") { Node(it) })

    /** Signal `node_deselected(node: Node)`; see [TypedSignal]. */
    val nodeDeselected: Signal1<Node>
        @JvmName("nodeDeselectedTypedSignal")
        get() = Signal1(this, "node_deselected", SignalArgType.objectOf("Node") { Node(it) })

    /** Signal `frame_rect_changed(frame: GraphFrame, new_rect: Rect2)`; see [TypedSignal]. On iOS a Rect2 argument is not delivered yet: a connection reports a script error. */
    val frameRectChanged: Signal2<GraphFrame, Rect2>
        @JvmName("frameRectChangedTypedSignal")
        get() = Signal2(this, "frame_rect_changed", SignalArgType.objectOf("GraphFrame") { GraphFrame(it) }, SignalArgType.valueOf<Rect2>("Rect2", Rect2::class))

    /** Signal `popup_request(at_position: Vector2)`; see [TypedSignal]. */
    val popupRequest: Signal1<Vector2>
        @JvmName("popupRequestTypedSignal")
        get() = Signal1(this, "popup_request", SignalArgType.valueOf<Vector2>("Vector2", Vector2::class))

    /** Signal `begin_node_move()`; see [TypedSignal]. */
    val beginNodeMove: Signal0
        @JvmName("beginNodeMoveTypedSignal")
        get() = Signal0(this, "begin_node_move")

    /** Signal `end_node_move()`; see [TypedSignal]. */
    val endNodeMove: Signal0
        @JvmName("endNodeMoveTypedSignal")
        get() = Signal0(this, "end_node_move")

    /** Signal `graph_elements_linked_to_frame_request(elements: Array, frame: StringName)`; see [TypedSignal]. On iOS a Array argument is not delivered yet: a connection reports a script error. */
    val graphElementsLinkedToFrameRequest: Signal2<List<Any?>, String>
        @JvmName("graphElementsLinkedToFrameRequestTypedSignal")
        get() = Signal2(this, "graph_elements_linked_to_frame_request", SignalArgType.valueOf<List<Any?>>("Array", List::class), SignalArgType.STRING)

    /** Signal `scroll_offset_changed(offset: Vector2)`; see [TypedSignal]. */
    val scrollOffsetChanged: Signal1<Vector2>
        @JvmName("scrollOffsetChangedTypedSignal")
        get() = Signal1(this, "scroll_offset_changed", SignalArgType.valueOf<Vector2>("Vector2", Vector2::class))

    object Signals {
        const val connectionRequest: String = "connection_request"
        const val disconnectionRequest: String = "disconnection_request"
        const val connectionToEmpty: String = "connection_to_empty"
        const val connectionFromEmpty: String = "connection_from_empty"
        const val connectionDragStarted: String = "connection_drag_started"
        const val connectionDragEnded: String = "connection_drag_ended"
        const val copyNodesRequest: String = "copy_nodes_request"
        const val cutNodesRequest: String = "cut_nodes_request"
        const val pasteNodesRequest: String = "paste_nodes_request"
        const val duplicateNodesRequest: String = "duplicate_nodes_request"
        const val deleteNodesRequest: String = "delete_nodes_request"
        const val nodeSelected: String = "node_selected"
        const val nodeDeselected: String = "node_deselected"
        const val frameRectChanged: String = "frame_rect_changed"
        const val popupRequest: String = "popup_request"
        const val beginNodeMove: String = "begin_node_move"
        const val endNodeMove: String = "end_node_move"
        const val graphElementsLinkedToFrameRequest: String = "graph_elements_linked_to_frame_request"
        const val scrollOffsetChanged: String = "scroll_offset_changed"
    }

    /**
     * Godot's `GraphEdit.PanningScheme` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`GraphEdit.PanningScheme.<NAME>`).
     *
     * Generated from Godot docs: GraphEdit.PanningScheme
     */
    @JvmInline
    value class PanningScheme(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Mouse Wheel will zoom, Ctrl + Mouse Wheel will move the view.
             *
             * Generated from Godot docs: GraphEdit.SCROLL_ZOOMS
             */
            val ZOOMS: PanningScheme get() = PanningScheme(0L)
            /**
             * Mouse Wheel will move the view, Ctrl + Mouse Wheel will zoom.
             *
             * Generated from Godot docs: GraphEdit.SCROLL_PANS
             */
            val PANS: PanningScheme get() = PanningScheme(1L)
        }
    }

    /**
     * Godot's `GraphEdit.GridPattern` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`GraphEdit.GridPattern.<NAME>`).
     *
     * Generated from Godot docs: GraphEdit.GridPattern
     */
    @JvmInline
    value class GridPattern(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Draw the grid using solid lines.
             *
             * Generated from Godot docs: GraphEdit.GRID_PATTERN_LINES
             */
            val LINES: GridPattern get() = GridPattern(0L)
            /**
             * Draw the grid using dots.
             *
             * Generated from Godot docs: GraphEdit.GRID_PATTERN_DOTS
             */
            val DOTS: GridPattern get() = GridPattern(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GraphEdit? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): GraphEdit? =
            if (handle.address() == 0L) null else GraphEdit(GodotHandle(handle))
    }

    private object Binds {
        private const val CONNECT_NODE_HASH = 1376144231L
        @JvmField
        val connectNodeBind =
            ObjectCalls.getMethodBind("GraphEdit", "connect_node", CONNECT_NODE_HASH)

        private const val IS_NODE_CONNECTED_HASH = 4216241294L
        @JvmField
        val isNodeConnectedBind =
            ObjectCalls.getMethodBind("GraphEdit", "is_node_connected", IS_NODE_CONNECTED_HASH)

        private const val DISCONNECT_NODE_HASH = 1933654315L
        @JvmField
        val disconnectNodeBind =
            ObjectCalls.getMethodBind("GraphEdit", "disconnect_node", DISCONNECT_NODE_HASH)

        private const val SET_CONNECTION_ACTIVITY_HASH = 1141899943L
        @JvmField
        val setConnectionActivityBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_connection_activity", SET_CONNECTION_ACTIVITY_HASH)

        private const val SET_CONNECTIONS_HASH = 381264803L
        @JvmField
        val setConnectionsBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_connections", SET_CONNECTIONS_HASH)

        private const val GET_CONNECTION_LIST_HASH = 3995934104L
        @JvmField
        val getConnectionListBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_connection_list", GET_CONNECTION_LIST_HASH)

        private const val GET_CONNECTION_COUNT_HASH = 861718734L
        @JvmField
        val getConnectionCountBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_connection_count", GET_CONNECTION_COUNT_HASH)

        private const val GET_CLOSEST_CONNECTION_AT_POINT_HASH = 453879819L
        @JvmField
        val getClosestConnectionAtPointBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_closest_connection_at_point", GET_CLOSEST_CONNECTION_AT_POINT_HASH)

        private const val GET_CONNECTION_LIST_FROM_NODE_HASH = 3147814860L
        @JvmField
        val getConnectionListFromNodeBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_connection_list_from_node", GET_CONNECTION_LIST_FROM_NODE_HASH)

        private const val GET_CONNECTIONS_INTERSECTING_WITH_RECT_HASH = 2709748719L
        @JvmField
        val getConnectionsIntersectingWithRectBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_connections_intersecting_with_rect", GET_CONNECTIONS_INTERSECTING_WITH_RECT_HASH)

        private const val CLEAR_CONNECTIONS_HASH = 3218959716L
        @JvmField
        val clearConnectionsBind =
            ObjectCalls.getMethodBind("GraphEdit", "clear_connections", CLEAR_CONNECTIONS_HASH)

        private const val FORCE_CONNECTION_DRAG_END_HASH = 3218959716L
        @JvmField
        val forceConnectionDragEndBind =
            ObjectCalls.getMethodBind("GraphEdit", "force_connection_drag_end", FORCE_CONNECTION_DRAG_END_HASH)

        private const val GET_SCROLL_OFFSET_HASH = 3341600327L
        @JvmField
        val getScrollOffsetBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_scroll_offset", GET_SCROLL_OFFSET_HASH)

        private const val SET_SCROLL_OFFSET_HASH = 743155724L
        @JvmField
        val setScrollOffsetBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_scroll_offset", SET_SCROLL_OFFSET_HASH)

        private const val ADD_VALID_RIGHT_DISCONNECT_TYPE_HASH = 1286410249L
        @JvmField
        val addValidRightDisconnectTypeBind =
            ObjectCalls.getMethodBind("GraphEdit", "add_valid_right_disconnect_type", ADD_VALID_RIGHT_DISCONNECT_TYPE_HASH)

        private const val REMOVE_VALID_RIGHT_DISCONNECT_TYPE_HASH = 1286410249L
        @JvmField
        val removeValidRightDisconnectTypeBind =
            ObjectCalls.getMethodBind("GraphEdit", "remove_valid_right_disconnect_type", REMOVE_VALID_RIGHT_DISCONNECT_TYPE_HASH)

        private const val ADD_VALID_LEFT_DISCONNECT_TYPE_HASH = 1286410249L
        @JvmField
        val addValidLeftDisconnectTypeBind =
            ObjectCalls.getMethodBind("GraphEdit", "add_valid_left_disconnect_type", ADD_VALID_LEFT_DISCONNECT_TYPE_HASH)

        private const val REMOVE_VALID_LEFT_DISCONNECT_TYPE_HASH = 1286410249L
        @JvmField
        val removeValidLeftDisconnectTypeBind =
            ObjectCalls.getMethodBind("GraphEdit", "remove_valid_left_disconnect_type", REMOVE_VALID_LEFT_DISCONNECT_TYPE_HASH)

        private const val ADD_VALID_CONNECTION_TYPE_HASH = 3937882851L
        @JvmField
        val addValidConnectionTypeBind =
            ObjectCalls.getMethodBind("GraphEdit", "add_valid_connection_type", ADD_VALID_CONNECTION_TYPE_HASH)

        private const val REMOVE_VALID_CONNECTION_TYPE_HASH = 3937882851L
        @JvmField
        val removeValidConnectionTypeBind =
            ObjectCalls.getMethodBind("GraphEdit", "remove_valid_connection_type", REMOVE_VALID_CONNECTION_TYPE_HASH)

        private const val IS_VALID_CONNECTION_TYPE_HASH = 2522259332L
        @JvmField
        val isValidConnectionTypeBind =
            ObjectCalls.getMethodBind("GraphEdit", "is_valid_connection_type", IS_VALID_CONNECTION_TYPE_HASH)

        private const val GET_CONNECTION_LINE_HASH = 3932192302L
        @JvmField
        val getConnectionLineBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_connection_line", GET_CONNECTION_LINE_HASH)

        private const val ATTACH_GRAPH_ELEMENT_TO_FRAME_HASH = 3740211285L
        @JvmField
        val attachGraphElementToFrameBind =
            ObjectCalls.getMethodBind("GraphEdit", "attach_graph_element_to_frame", ATTACH_GRAPH_ELEMENT_TO_FRAME_HASH)

        private const val DETACH_GRAPH_ELEMENT_FROM_FRAME_HASH = 3304788590L
        @JvmField
        val detachGraphElementFromFrameBind =
            ObjectCalls.getMethodBind("GraphEdit", "detach_graph_element_from_frame", DETACH_GRAPH_ELEMENT_FROM_FRAME_HASH)

        private const val GET_ELEMENT_FRAME_HASH = 988084372L
        @JvmField
        val getElementFrameBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_element_frame", GET_ELEMENT_FRAME_HASH)

        private const val GET_ATTACHED_NODES_OF_FRAME_HASH = 689397652L
        @JvmField
        val getAttachedNodesOfFrameBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_attached_nodes_of_frame", GET_ATTACHED_NODES_OF_FRAME_HASH)

        private const val SET_PANNING_SCHEME_HASH = 18893313L
        @JvmField
        val setPanningSchemeBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_panning_scheme", SET_PANNING_SCHEME_HASH)

        private const val GET_PANNING_SCHEME_HASH = 549924446L
        @JvmField
        val getPanningSchemeBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_panning_scheme", GET_PANNING_SCHEME_HASH)

        private const val SET_ZOOM_HASH = 373806689L
        @JvmField
        val setZoomBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_zoom", SET_ZOOM_HASH)

        private const val GET_ZOOM_HASH = 1740695150L
        @JvmField
        val getZoomBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_zoom", GET_ZOOM_HASH)

        private const val SET_ZOOM_MIN_HASH = 373806689L
        @JvmField
        val setZoomMinBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_zoom_min", SET_ZOOM_MIN_HASH)

        private const val GET_ZOOM_MIN_HASH = 1740695150L
        @JvmField
        val getZoomMinBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_zoom_min", GET_ZOOM_MIN_HASH)

        private const val SET_ZOOM_MAX_HASH = 373806689L
        @JvmField
        val setZoomMaxBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_zoom_max", SET_ZOOM_MAX_HASH)

        private const val GET_ZOOM_MAX_HASH = 1740695150L
        @JvmField
        val getZoomMaxBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_zoom_max", GET_ZOOM_MAX_HASH)

        private const val SET_ZOOM_STEP_HASH = 373806689L
        @JvmField
        val setZoomStepBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_zoom_step", SET_ZOOM_STEP_HASH)

        private const val GET_ZOOM_STEP_HASH = 1740695150L
        @JvmField
        val getZoomStepBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_zoom_step", GET_ZOOM_STEP_HASH)

        private const val SET_SHOW_GRID_HASH = 2586408642L
        @JvmField
        val setShowGridBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_show_grid", SET_SHOW_GRID_HASH)

        private const val IS_SHOWING_GRID_HASH = 36873697L
        @JvmField
        val isShowingGridBind =
            ObjectCalls.getMethodBind("GraphEdit", "is_showing_grid", IS_SHOWING_GRID_HASH)

        private const val SET_GRID_PATTERN_HASH = 1074098205L
        @JvmField
        val setGridPatternBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_grid_pattern", SET_GRID_PATTERN_HASH)

        private const val GET_GRID_PATTERN_HASH = 1286127528L
        @JvmField
        val getGridPatternBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_grid_pattern", GET_GRID_PATTERN_HASH)

        private const val SET_SNAPPING_ENABLED_HASH = 2586408642L
        @JvmField
        val setSnappingEnabledBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_snapping_enabled", SET_SNAPPING_ENABLED_HASH)

        private const val IS_SNAPPING_ENABLED_HASH = 36873697L
        @JvmField
        val isSnappingEnabledBind =
            ObjectCalls.getMethodBind("GraphEdit", "is_snapping_enabled", IS_SNAPPING_ENABLED_HASH)

        private const val SET_SNAPPING_DISTANCE_HASH = 1286410249L
        @JvmField
        val setSnappingDistanceBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_snapping_distance", SET_SNAPPING_DISTANCE_HASH)

        private const val GET_SNAPPING_DISTANCE_HASH = 3905245786L
        @JvmField
        val getSnappingDistanceBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_snapping_distance", GET_SNAPPING_DISTANCE_HASH)

        private const val SET_CONNECTION_LINES_CURVATURE_HASH = 373806689L
        @JvmField
        val setConnectionLinesCurvatureBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_connection_lines_curvature", SET_CONNECTION_LINES_CURVATURE_HASH)

        private const val GET_CONNECTION_LINES_CURVATURE_HASH = 1740695150L
        @JvmField
        val getConnectionLinesCurvatureBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_connection_lines_curvature", GET_CONNECTION_LINES_CURVATURE_HASH)

        private const val SET_CONNECTION_LINES_THICKNESS_HASH = 373806689L
        @JvmField
        val setConnectionLinesThicknessBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_connection_lines_thickness", SET_CONNECTION_LINES_THICKNESS_HASH)

        private const val GET_CONNECTION_LINES_THICKNESS_HASH = 1740695150L
        @JvmField
        val getConnectionLinesThicknessBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_connection_lines_thickness", GET_CONNECTION_LINES_THICKNESS_HASH)

        private const val SET_CONNECTION_LINES_ANTIALIASED_HASH = 2586408642L
        @JvmField
        val setConnectionLinesAntialiasedBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_connection_lines_antialiased", SET_CONNECTION_LINES_ANTIALIASED_HASH)

        private const val IS_CONNECTION_LINES_ANTIALIASED_HASH = 36873697L
        @JvmField
        val isConnectionLinesAntialiasedBind =
            ObjectCalls.getMethodBind("GraphEdit", "is_connection_lines_antialiased", IS_CONNECTION_LINES_ANTIALIASED_HASH)

        private const val SET_MINIMAP_SIZE_HASH = 743155724L
        @JvmField
        val setMinimapSizeBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_minimap_size", SET_MINIMAP_SIZE_HASH)

        private const val GET_MINIMAP_SIZE_HASH = 3341600327L
        @JvmField
        val getMinimapSizeBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_minimap_size", GET_MINIMAP_SIZE_HASH)

        private const val SET_MINIMAP_OPACITY_HASH = 373806689L
        @JvmField
        val setMinimapOpacityBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_minimap_opacity", SET_MINIMAP_OPACITY_HASH)

        private const val GET_MINIMAP_OPACITY_HASH = 1740695150L
        @JvmField
        val getMinimapOpacityBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_minimap_opacity", GET_MINIMAP_OPACITY_HASH)

        private const val SET_MINIMAP_ENABLED_HASH = 2586408642L
        @JvmField
        val setMinimapEnabledBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_minimap_enabled", SET_MINIMAP_ENABLED_HASH)

        private const val IS_MINIMAP_ENABLED_HASH = 36873697L
        @JvmField
        val isMinimapEnabledBind =
            ObjectCalls.getMethodBind("GraphEdit", "is_minimap_enabled", IS_MINIMAP_ENABLED_HASH)

        private const val SET_SHOW_MENU_HASH = 2586408642L
        @JvmField
        val setShowMenuBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_show_menu", SET_SHOW_MENU_HASH)

        private const val IS_SHOWING_MENU_HASH = 36873697L
        @JvmField
        val isShowingMenuBind =
            ObjectCalls.getMethodBind("GraphEdit", "is_showing_menu", IS_SHOWING_MENU_HASH)

        private const val SET_SHOW_ZOOM_LABEL_HASH = 2586408642L
        @JvmField
        val setShowZoomLabelBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_show_zoom_label", SET_SHOW_ZOOM_LABEL_HASH)

        private const val IS_SHOWING_ZOOM_LABEL_HASH = 36873697L
        @JvmField
        val isShowingZoomLabelBind =
            ObjectCalls.getMethodBind("GraphEdit", "is_showing_zoom_label", IS_SHOWING_ZOOM_LABEL_HASH)

        private const val SET_SHOW_GRID_BUTTONS_HASH = 2586408642L
        @JvmField
        val setShowGridButtonsBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_show_grid_buttons", SET_SHOW_GRID_BUTTONS_HASH)

        private const val IS_SHOWING_GRID_BUTTONS_HASH = 36873697L
        @JvmField
        val isShowingGridButtonsBind =
            ObjectCalls.getMethodBind("GraphEdit", "is_showing_grid_buttons", IS_SHOWING_GRID_BUTTONS_HASH)

        private const val SET_SHOW_ZOOM_BUTTONS_HASH = 2586408642L
        @JvmField
        val setShowZoomButtonsBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_show_zoom_buttons", SET_SHOW_ZOOM_BUTTONS_HASH)

        private const val IS_SHOWING_ZOOM_BUTTONS_HASH = 36873697L
        @JvmField
        val isShowingZoomButtonsBind =
            ObjectCalls.getMethodBind("GraphEdit", "is_showing_zoom_buttons", IS_SHOWING_ZOOM_BUTTONS_HASH)

        private const val SET_SHOW_MINIMAP_BUTTON_HASH = 2586408642L
        @JvmField
        val setShowMinimapButtonBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_show_minimap_button", SET_SHOW_MINIMAP_BUTTON_HASH)

        private const val IS_SHOWING_MINIMAP_BUTTON_HASH = 36873697L
        @JvmField
        val isShowingMinimapButtonBind =
            ObjectCalls.getMethodBind("GraphEdit", "is_showing_minimap_button", IS_SHOWING_MINIMAP_BUTTON_HASH)

        private const val SET_SHOW_ARRANGE_BUTTON_HASH = 2586408642L
        @JvmField
        val setShowArrangeButtonBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_show_arrange_button", SET_SHOW_ARRANGE_BUTTON_HASH)

        private const val IS_SHOWING_ARRANGE_BUTTON_HASH = 36873697L
        @JvmField
        val isShowingArrangeButtonBind =
            ObjectCalls.getMethodBind("GraphEdit", "is_showing_arrange_button", IS_SHOWING_ARRANGE_BUTTON_HASH)

        private const val SET_RIGHT_DISCONNECTS_HASH = 2586408642L
        @JvmField
        val setRightDisconnectsBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_right_disconnects", SET_RIGHT_DISCONNECTS_HASH)

        private const val IS_RIGHT_DISCONNECTS_ENABLED_HASH = 36873697L
        @JvmField
        val isRightDisconnectsEnabledBind =
            ObjectCalls.getMethodBind("GraphEdit", "is_right_disconnects_enabled", IS_RIGHT_DISCONNECTS_ENABLED_HASH)

        private const val SET_TYPE_NAMES_HASH = 4155329257L
        @JvmField
        val setTypeNamesBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_type_names", SET_TYPE_NAMES_HASH)

        private const val GET_TYPE_NAMES_HASH = 3102165223L
        @JvmField
        val getTypeNamesBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_type_names", GET_TYPE_NAMES_HASH)

        private const val GET_MENU_HBOX_HASH = 3590609951L
        @JvmField
        val getMenuHboxBind =
            ObjectCalls.getMethodBind("GraphEdit", "get_menu_hbox", GET_MENU_HBOX_HASH)

        private const val ARRANGE_NODES_HASH = 3218959716L
        @JvmField
        val arrangeNodesBind =
            ObjectCalls.getMethodBind("GraphEdit", "arrange_nodes", ARRANGE_NODES_HASH)

        private const val SET_SELECTED_HASH = 1078189570L
        @JvmField
        val setSelectedBind =
            ObjectCalls.getMethodBind("GraphEdit", "set_selected", SET_SELECTED_HASH)
    }
}

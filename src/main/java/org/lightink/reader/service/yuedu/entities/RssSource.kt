//package io.legado.app.data.entities
//
//import android.os.Parcelable
//import androidx.room.Entity
//import androidx.room.Index
//import androidx.room.PrimaryKey
//import io.legado.app.App
//import io.legado.app.constant.AppConst
//import io.legado.app.help.JsExtensions
//import io.legado.app.utils.GSON
//import io.legado.app.utils.fromJsonObject
//import io.legado.app.utils.getPrefString
//
//import java.util.*
//import javax.script.SimpleBindings
//
//@Parcelize
//@Entity(tableName = "rssSources", indices = [(Index(value = ["sourceUrl"], unique = false))])
//data class RssSource(
//    @PrimaryKey
//    var sourceUrl: String = "",
//    var sourceName: String = "",
//    var sourceIcon: String = "",
//    var sourceGroup: String? = null,
//    var enabled: Boolean = true,
//    //列表规则
//    var ruleArticles: String? = null,
//    var ruleNextPage: String? = null,
//    var ruleTitle: String? = null,
//    var rulePubDate: String? = null,
//    //webView规则
//    var ruleDescription: String? = null,
//    var ruleImage: String? = null,
//    var ruleLink: String? = null,
//    var ruleContent: String? = null,
//    var header: String? = null,
//    var enableJs: Boolean = false,
//    var loadWithBaseUrl: Boolean = false,
//    var customOrder: Int = 0
//) : Parcelable {
//
//    @Throws(Exception::class)
//    fun getHeaderMap(): Map<String, String> {
//        val headerMap = HashMap<String, String>()
//        headerMap[AppConst.UA_NAME] = App.INSTANCE.getPrefString("user_agent") ?: AppConst.userAgent
//        header?.let {
//            val header1 = when {
//                it.startsWith("@js:", true) ->
//                    evalJS(it.substring(4)).toString()
//                it.startsWith("<js>", true) ->
//                    evalJS(it.substring(4, it.lastIndexOf("<"))).toString()
//                else -> it
//            }
//            GSON.fromJsonObject<Map<String, String>>(header1)?.let { map ->
//                headerMap.putAll(map)
//            }
//        }
//        return headerMap
//    }
//
//    /**
//     * 执行JS
//     */
//    @Throws(Exception::class)
//    private fun evalJS(jsStr: String): Any {
//        val bindings = SimpleBindings()
//        bindings["java"] = JsExtensions
//        return AppConst.SCRIPT_ENGINE.eval(jsStr, bindings)
//    }
//
//    fun equal(source: RssSource): Boolean {
//        return equal(sourceUrl, source.sourceUrl)
//                && equal(sourceIcon, source.sourceIcon)
//                && enabled == source.enabled
//                && equal(sourceGroup, source.sourceGroup)
//                && equal(ruleArticles, source.ruleArticles)
//                && equal(ruleNextPage, source.ruleNextPage)
//                && equal(ruleTitle, source.ruleTitle)
//                && equal(rulePubDate, source.rulePubDate)
//                && equal(ruleDescription, source.ruleDescription)
//                && equal(ruleLink, source.ruleLink)
//                && equal(ruleContent, source.ruleContent)
//                && enableJs == source.enableJs
//                && loadWithBaseUrl == source.loadWithBaseUrl
//    }
//
//    private fun equal(a: String?, b: String?): Boolean {
//        return a == b || (a.isNullOrEmpty() && b.isNullOrEmpty())
//    }
//
//}
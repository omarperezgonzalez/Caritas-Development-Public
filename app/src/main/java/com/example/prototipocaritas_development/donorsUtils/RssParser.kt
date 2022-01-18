package com.example.prototipocaritas_development.donorsUtils

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class RssParser {
    private val rssItems = ArrayList<RssItem>()
    private var rssItem : RssItem ?= null
    private var text: String? = null


    fun parseBody(parrafo : String) : String {
        val i = parrafo.indexOf('>') + 1
        val j = parrafo.indexOf('<', 3)
        val AuxString = parrafo.substring(i, j)
        return AuxString
    }

    private fun setComillas(content: String): String {
        val LdoubleQuotes = "“" // “
        val RdoubleQuotes = "”" // ”

        val contenido = content.replace("&#8220;", LdoubleQuotes)

        return contenido.replace("&#8221;", RdoubleQuotes)
    }

    fun parse(inputStream: InputStream):List<RssItem> {

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            var foundItem = false
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagname = parser.name
                when (eventType) {

                    XmlPullParser.START_TAG -> if (tagname.equals("item", ignoreCase = true)) {
                        // create a new instance of employee
                        foundItem = true
                        rssItem = RssItem()
                    }
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> if (tagname.equals("item", ignoreCase = true)) {
                        // add employee object to list
                        rssItem?.let { rssItems.add(it) }
                        foundItem = false
                    } else if ( foundItem && tagname.equals("title", ignoreCase = true)) {
                        rssItem!!.title = text.toString()
                    } else if (foundItem && tagname.equals("link", ignoreCase = true)) {
                        rssItem!!.link = text.toString()
                        val url = text.toString()
                    } else if (foundItem && tagname.equals("pubDate", ignoreCase = true)) {
                        rssItem!!.pubDate = text.toString().substring(0, (text.toString().length - 6))
                    } else if (foundItem && tagname.equals("category", ignoreCase = true)) {
                        rssItem!!.category = text.toString()
                    } else if (foundItem && tagname.equals("description", ignoreCase = true)) {
                        val AuxString = text.toString()
                        val ans = parseBody(AuxString)
                        rssItem!!.description = ans
                    } else if (foundItem && tagname.equals("encoded", ignoreCase = true)) {
                        val contenido = setComillas(text.toString().replace("¶", ""))
                        rssItem!!.content = contenido
                        // Log.i("content",  text.toString())
                    }
                }
                eventType = parser.next()
            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return rssItems
    }
}
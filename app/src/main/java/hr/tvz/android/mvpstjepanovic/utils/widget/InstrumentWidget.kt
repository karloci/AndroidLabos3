package hr.tvz.android.mvpstjepanovic.utils.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import dagger.hilt.android.EntryPointAccessors
import hr.tvz.android.mvpstjepanovic.R
import hr.tvz.android.mvpstjepanovic.instrument.repository.InstrumentRepositoryEntryPoint
import kotlinx.coroutines.*

/**
 * Implementation of App Widget functionality.
 */
class InstrumentWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val entryPoint = EntryPointAccessors.fromApplication(
        context,
        InstrumentRepositoryEntryPoint::class.java
    )
    val repo = entryPoint.instrumentRepository()

    val views = RemoteViews(context.packageName, R.layout.instrument_widget)

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val instruments = repo.getAllInstruments()
            val widgetText = if (instruments.isNotEmpty()) {
                instruments.last().name
            } else {
                context.getString(R.string.appwidget_text)
            }

            withContext(Dispatchers.Main) {
                views.setTextViewText(R.id.instrumentWidgetName, widgetText)
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                views.setTextViewText(R.id.instrumentWidgetName, context.getString(R.string.appwidget_text))
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        }
    }
}
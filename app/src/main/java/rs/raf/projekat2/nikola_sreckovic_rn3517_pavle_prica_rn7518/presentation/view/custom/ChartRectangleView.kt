package rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.presentation.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import rs.raf.projekat2.nikola_sreckovic_rn3517_pavle_prica_rn7518.R

class ChartRectangleView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var percentage = 0.5

    private var rect: RectF = RectF()
    private var paint: Paint = Paint().also {
        it.isAntiAlias = true
        it.color = ContextCompat.getColor(context, R.color.colorPrimary)
        it.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val left = 0
        val top = height - (height * percentage) * 1.0
        val bottom = height  * 1.0
        val right = width * 1.0
        rect.set(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
        canvas?.drawRect(rect, paint)
    }


}
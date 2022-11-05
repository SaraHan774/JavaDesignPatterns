package facade

// https://www.tutorialspoint.com/design_pattern/facade_pattern.htm
interface Shape {
    fun draw()
}

class Rectangle : Shape {
    override fun draw() {
        println("draw: Rectangle")
    }
}

class Square : Shape {
    override fun draw() {
        println("draw: Square")
    }
}

class Circle : Shape {
    override fun draw() {
        println("draw: Circle")
    }
}

// facade class
class ShapeMaker(
    private val rectangle: Rectangle,
    private val square: Square,
    private val circle: Circle,
) {
    fun drawCircle() {
        circle.draw()
    }

    fun drawRectangle() {
        rectangle.draw()
    }

    fun drawSquare() {
        square.draw()
    }
}

fun main() {
    val maker = ShapeMaker(
        Rectangle(), Square(), Circle()
    )

    maker.drawRectangle()
    maker.drawCircle()
    maker.drawSquare()
}
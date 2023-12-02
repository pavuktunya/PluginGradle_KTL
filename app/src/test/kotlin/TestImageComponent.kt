package component

import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.github.quillraven.fleks.ComponentListener
import com.github.quillraven.fleks.Entity

class TestImageComponent:Comparable<TestImageComponent>{
    lateinit var image:Image
    override fun compareTo(other: TestImageComponent): Int {
        val yDiff = other.image.y.compareTo(image.y)
        return if(yDiff != 0){
            yDiff
        } else{
            other.image.x.compareTo(image.x)
        }
    }

    companion object{
        class ImageComponentListener(
            private val stage: Stage
        ): ComponentListener<TestImageComponent>{
            override fun onComponentAdded(entity: Entity, component: TestImageComponent) {
                stage.addActor(component.image)
            }

            override fun onComponentRemoved(entity: Entity, component: TestImageComponent) {
                stage.root.removeActor(component.image)
            }
        }
    }
}


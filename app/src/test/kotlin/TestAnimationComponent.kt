package component

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

enum class AnimationType{
    IDLE, RUN, JUMP, OPEN;
    val atlasKey: String = this.toString().lowercase()
}
class AnimationComponent(
    var atlasKey:String="",
    var stateTime:Float= 0f,
    var playMode: PlayMode=PlayMode.LOOP

) {
    lateinit var animation: Animation<TextureRegionDrawable>
    var nextAnimation: String=""

    fun nextAnimation(atlasKey: String, type:AnimationType){
        this.atlasKey=atlasKey
        nextAnimation="${atlasKey}/${type.atlasKey}"
    }

}

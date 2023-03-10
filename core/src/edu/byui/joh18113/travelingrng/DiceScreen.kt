package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight
import com.badlogic.gdx.graphics.g3d.utils.AnimationController
import com.badlogic.gdx.graphics.g3d.utils.AnimationController.AnimationListener
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController
import kotlin.math.sqrt

class DiceScreen(val game: Main) : Screen, AnimationListener {
    private var camera: PerspectiveCamera? = null
    private var environment: Environment? = null
    private var camController: CameraInputController? = null
    private var models: Model? = null
    private val instances: ArrayList<ModelInstance> = ArrayList()
    private val controllers: ArrayList<AnimationController> = ArrayList()
    private var loading: Boolean = true
    private val dice6: Die = Die(6)
    private var numDice = 0
    private var table: ModelInstance? = null
    private var time = 0f

    override fun show() {
        camera = PerspectiveCamera(67F, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera?.position?.set(35f, 35f, 10f)
        camera?.lookAt(12f, 0f, 9f)
        camera?.near = 1f
        camera?.far = 300f
        camera?.update()

        environment = Environment()
        environment?.set(ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f))
        environment?.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, -1.2f, -0.8f, -0.2f))
        environment?.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, 1.2f, 0.8f, 0.2f))
        camController = CameraInputController(camera)

        Gdx.input.inputProcessor = camController
        numDice = Settings.numDice
        loading = true
    }

    private fun updateDice() {
        instances.clear()
        var count = 0
        var x = 0f
        var z = 0f
        val int = sqrt(numDice.toDouble())
        while (count < numDice) {
            var y = 0
            while (y < int && count < numDice) {
                val coin = ModelInstance(models, "Dice")
                coin.transform.setToTranslation(z, 0f, x)
                controllers.add(AnimationController(coin))
                instances.add(coin)
                y++
                count++
                x += 5f
            }
            x = 0f
            z += 5f
        }
    }

    private fun load() {
        models = Assets.models.get("Models.g3db", Model().javaClass)
        table = ModelInstance(models, "Plane")
        updateDice()
        loading = false
    }

    override fun render(delta: Float) {
        if (loading && Assets.models.update()) {
            load()
        }
        camController?.update()
        Gdx.gl.glViewport(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        game.mBatch?.begin(camera)
        game.mBatch?.render(instances, environment)
        if (!loading) game.mBatch?.render(table, environment)
        game.mBatch?.end()
        time += delta

        if (!loading && Gdx.input.isKeyPressed(Input.Keys.SPACE) && (time > 0.25f)) {
            for (c in controllers) {
                c.animate(dice6.roll(), 1f)
            }
            time = 0f
        }
        for (c in controllers) c.update(delta)
        if (Gdx.input.isKeyPressed(Input.Keys.K)) game.screen = MainMenuScreen(game)
    }

    override fun resize(width: Int, height: Int) {
        //TODO("Not yet implemented")
    }

    override fun pause() {
        //TODO("Not yet implemented")
    }

    override fun resume() {
        //TODO("Not yet implemented")
    }

    override fun hide() {
    }

    override fun dispose() {
        instances.clear()
        controllers.clear()
        environment?.clear()
        models?.dispose()
    }

    override fun onEnd(animation: AnimationController.AnimationDesc?) {
        //TODO("Not yet implemented")
    }

    override fun onLoop(animation: AnimationController.AnimationDesc?) {
        //TODO("Not yet implemented")
    }

}

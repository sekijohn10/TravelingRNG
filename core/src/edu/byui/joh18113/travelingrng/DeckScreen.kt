package edu.byui.joh18113.travelingrng

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight
import com.badlogic.gdx.graphics.g3d.utils.AnimationController
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController

class DeckScreen(val game: Main) : Screen {
    var camera: PerspectiveCamera? = null
    var environment: Environment? = null
    var camController: CameraInputController? = null
    var models: Model? = null
    val instances: ArrayList<ModelInstance> = ArrayList()
    val controllers: ArrayList<AnimationController> = ArrayList()
    var loading: Boolean = false

    override fun show() {
        camera = PerspectiveCamera(67F, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera?.position?.set(10f, 15f, 10f)
        camera?.lookAt(0f, 0f, 0f)
        camera?.near = 1f
        camera?.far = 300f
        camera?.update()

        environment = Environment()
        environment?.set(ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f))
        environment?.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, -1.2f, -0.8f, -0.2f))
        environment?.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, 1.2f, 0.8f, 0.2f))
        camController = CameraInputController(camera)

        Gdx.input.inputProcessor = camController
        loading = true
    }

    private fun load() {
        instances.clear()
        models = Assets.models.get("Models.g3db", Model().javaClass)
        val table = ModelInstance(models, "Plane")
        var count = 0
        var x = 0f
        var z = 0f

        instances.add(table)
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
        game.mBatch?.end()

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
        //TODO("Not yet implemented")
    }

    override fun dispose() {
        instances.clear()
        controllers.clear()
        environment?.clear()
        models?.dispose()
    }

}

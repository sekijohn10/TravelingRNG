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
    val deckModel: ArrayList<ModelInstance> = ArrayList()
    val instances: ArrayList<ModelInstance> = ArrayList()
    var table: ModelInstance? = null
    val controllers: ArrayList<AnimationController> = ArrayList()
    var loading: Boolean = true
    val deck = Deck()

    override fun show() {
        camera = PerspectiveCamera(67F, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera?.position?.set(0f, 25f, 15f)
        camera?.lookAt(0f, 0f, 0f)
        camera?.near = 1f
        camera?.far = 300f
        camera?.update()

        environment = Environment()
        environment?.set(ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f))
        environment?.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, -10f, 10f, -10f))
        environment?.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, 10f, 10f, 10f))
        camController = CameraInputController(camera)

        camController?.translateTarget
        Gdx.input.inputProcessor = camController
        loading = true
    }

    private fun fillDeck() {
        deckModel.clear()
        var count = 0
        var z = 0f
        while (count < 52) {
            val card = ModelInstance(models, "Blank")
            card.transform.setToTranslation(0f, z, -5f)
            deckModel.add(card)
            count++
            z += 0.025f
        }
    }

    private fun load() {
        models = game.assets.models.get("Models.g3db", Model().javaClass)
        fillDeck()
        table = ModelInstance(models, "Plane")
        loading = false
        drawCard()
    }

    private fun drawCard() {
        if (deckModel.isEmpty()) fillDeck()
        deckModel.removeLast()
        if (deck.deck.isEmpty()) deck.reset()
        val card = ModelInstance(models, deck.drawCard().value)
        card.transform.setToTranslation(
            -10f + (1.1f * (instances.size % 10)), 0f + (0.025f * (instances.size % 10)), 5f
        )
        instances.add(card)
    }

    override fun render(delta: Float) {
        if (loading && game.assets.models.update()) load()

        if (!loading && Gdx.input.isTouched) drawCard()

        camController?.update()
        Gdx.gl.glViewport(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        game.mBatch?.begin(camera)
        game.mBatch?.render(deckModel, environment)
        game.mBatch?.render(instances, environment)
        if (!loading) game.mBatch?.render(table, environment)
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
        deckModel.clear()
        controllers.clear()
        environment?.clear()
        models?.dispose()
        instances.clear()
    }

}

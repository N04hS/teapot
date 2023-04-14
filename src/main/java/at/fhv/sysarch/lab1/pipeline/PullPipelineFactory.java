package at.fhv.sysarch.lab1.pipeline;

import at.fhv.sysarch.lab1.animation.AnimationRenderer;
import at.fhv.sysarch.lab1.obj.Face;
import at.fhv.sysarch.lab1.obj.Model;
import at.fhv.sysarch.lab1.pipeline.filters.*;
import com.hackoeur.jglm.Matrices;
import javafx.animation.AnimationTimer;

public class PullPipelineFactory {
    public static AnimationTimer createPipeline(PipelineData pd) {
        Source source = new Source();
        Sink sink = new Sink(pd.getGraphicsContext());
        IFilter<Face> resize = new ResizeFilter();
        IFilter<Face> rotate = new RotationFilter();
        IFilter<Face> view = new ViewTransformFilter();

        Pipe<Face> connectSourceResize = new Pipe<>();
        Pipe<Face> connectResizeRotate = new Pipe<>();
        Pipe<Face> connectRotateView = new Pipe<>();
        Pipe<Face> connectViewSink = new Pipe<>();

        source.setSuccessor(connectSourceResize);
        connectSourceResize.setOutgoing(resize);
        resize.setSuccessor(connectResizeRotate);
        connectResizeRotate.setOutgoing(rotate);
        rotate.setSuccessor(connectRotateView);
        connectRotateView.setOutgoing(view);
        view.setSuccessor(connectViewSink);
        connectViewSink.setOutgoing(sink);

        // TODO: pull from the source (model)

        // TODO 1. perform model-view transformation from model to VIEW SPACE coordinates

        // TODO 2. perform backface culling in VIEW SPACE

        // TODO 3. perform depth sorting in VIEW SPACE

        // TODO 4. add coloring (space unimportant)

        // lighting can be switched on/off
        if (pd.isPerformLighting()) {
            // 4a. TODO perform lighting in VIEW SPACE
            
            // 5. TODO perform projection transformation on VIEW SPACE coordinates
        } else {
            // 5. TODO perform projection transformation
        }

        // TODO 6. perform perspective division to screen coordinates

        // TODO 7. feed into the sink (renderer)

        // returning an animation renderer which handles clearing of the
        // viewport and computation of the praction
        return new AnimationRenderer(pd) {
            private int pos = 0;
            // TODO rotation variable goes in here
            float elapsedTime = 0;

            /** This method is called for every frame from the JavaFX Animation
             * system (using an AnimationTimer, see AnimationRenderer). 
             * @param fraction the time which has passed since the last render call in a fraction of a second
             * @param model    the model to render 
             */
            @Override
            protected void render(float fraction, Model model) {
                pd.getGraphicsContext().setStroke(pd.getModelColor());

                Container c = new Container();
                float phi = (float) ((Math.PI*2*(elapsedTime+=fraction))/10);
                c.rotMat = Matrices.rotate(phi, pd.getModelRotAxis());
                c.viewMat = pd.getViewTransform();

                source.write(model, c);
                // TODO compute rotation in radians

                // TODO create new model rotation matrix using pd.getModelRotAxis and Matrices.rotate

                // TODO compute updated model-view tranformation

                // TODO update model-view filter

                // TODO trigger rendering of the pipeline
            }
        };
    }
}


package peacefulotter.engine.components;

import peacefulotter.engine.core.CoreEngine;
import peacefulotter.engine.core.maths.Matrix4f;
import peacefulotter.engine.core.maths.Vector3f;
import peacefulotter.engine.rendering.Window;
import peacefulotter.engine.rendering.shaders.transfomations.STransform;

public class Camera extends GameComponent
{
    public static Vector3f PLAYER_CAMERA() { return new Vector3f(0, 6.5f, 0f ); }
    private final Matrix4f projection;

    public Camera( float fovDeg, float aspectRatio, float zNear, float zFar )
    {
        this.projection = new Matrix4f().initPerspective( fovDeg, aspectRatio, zNear, zFar );
    }

    @Override
    public void addToEngine( CoreEngine engine )
    {
        engine.getRenderingEngine().setCamera( this );
    }

    public Matrix4f getViewProjection()
    {
        STransform cameraTransform = getTransform();
        Vector3f pos = cameraTransform.getTranslation().mul( -1 );
        Matrix4f cameraRotation = cameraTransform.getRotation().conjugate().toRotationMatrix();
        Matrix4f cameraTranslation = new Matrix4f().initTranslation( pos.getX(), pos.getY(), pos.getZ() );

        return projection.mul( cameraRotation.mul( cameraTranslation ) );
    }


    @Override
    public void update( float deltaTime )
    {
        // spotLight.getPointLight().setPosition( position );
        // spotLight.setDirection( forward );
    }

    //public void setInnerTranslation( Vector3f innerTranslation ) { this.innerTranslation = innerTranslation; }

    public static class CameraBuilder
    {
        public static Camera getDefaultCamera()
        {
            return new Camera( 70f, Window.getRatio(), 0.01f, 1000f );
        }
    }
}

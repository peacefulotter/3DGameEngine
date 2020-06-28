package peacefulotter.engine.rendering.shaders.transfomations;

import peacefulotter.engine.core.maths.Matrix4f;
import peacefulotter.engine.core.maths.Vector3f;

public class SRotation
{
    private Vector3f rotation = new Vector3f( 0, 0, 0 );

    public Matrix4f getRotationMatrix()
    {
        Matrix4f rotationMatrix = new Matrix4f().initRotation(
                rotation.getX(), rotation.getY(), rotation.getZ()
        );
        return rotationMatrix;
    }

    public Vector3f getRotation()
    {
        return rotation;
    }

    public void setRotation( Vector3f rotation )
    {
        this.rotation = rotation;
    }

    public void setRotation( float x, float y, float z )
    {
        rotation = new Vector3f( x, y, z );
    }
}
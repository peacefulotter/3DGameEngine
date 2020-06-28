package peacefulotter.engine.rendering.shaders.transfomations;

import peacefulotter.engine.core.maths.Matrix4f;
import peacefulotter.engine.core.maths.Vector3f;

public class STransform
{
    private final STranslation translation = new STranslation();
    private final SRotation rotation = new SRotation();
    private final SScale scale = new SScale();

    private STransform parent;

    public Matrix4f getTransformationMatrix()
    {
        return translation.getTranslationMatrix().mul(
                rotation.getRotationMatrix().mul(
                        scale.getScaleMatrix()
                ) );
    }

    public STransform setTranslation(float x, float y, float z )
    {
        translation.setTranslation( x, y , z );
        return this;
    }

    public STransform setTranslation(Vector3f vector )
    {
        translation.setTranslation( vector );
        return this;
    }

    public STransform setRotation(float x, float y, float z )
    {
        rotation.setRotation( x, y , z );
        return this;
    }

    public STransform setRotation( Vector3f vector )
    {
        rotation.setRotation( vector );
        return this;
    }

    public STransform setScale( float x, float y, float z )
    {
        scale.setScale( x, y, z );
        return this;
    }

    public STransform setScale( Vector3f vector )
    {
        scale.setScale( vector );
        return this;
    }

    public void setParent( STransform parent ) { this.parent = parent; }
}
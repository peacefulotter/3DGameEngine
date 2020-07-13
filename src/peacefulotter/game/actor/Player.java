package peacefulotter.game.actor;

import peacefulotter.engine.components.PhysicsObject;
import peacefulotter.engine.core.maths.Vector3f;
import peacefulotter.engine.utils.IO.Input;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static peacefulotter.engine.utils.IO.Input.*;

public class Player extends PhysicsObject
{
    private static final float GRAVITY = 55f;
    private static final float JUMP_HEIGHT = 20f;
    private static final Vector3f Y_AXIS = new Vector3f( 0, 1, 0 );

    private final float movingSensitivity;
    private float rotationSensitivity;
    private boolean isJumping, isCrouching;

    public Player()
    {
        this( 35, 130 );
    }

    public Player( float movingSensitivity, float rotationSensitivity )
    {
        super( Vector3f.getZero() );
        this.movingSensitivity = movingSensitivity;
        this.rotationSensitivity = rotationSensitivity;
    }

    @Override
    public void init()
    {
        Input.addKeyCallback( GLFW_KEY_W, (deltaTime ) -> move( getForward(), deltaTime ) );
        Input.addKeyCallback( GLFW_KEY_D, ( deltaTime ) -> move( getRight(),   deltaTime ) );
        Input.addKeyCallback( GLFW_KEY_S, ( deltaTime ) -> move( getBackward(), deltaTime ) );
        Input.addKeyCallback( GLFW_KEY_A, ( deltaTime ) -> move( getLeft(),   deltaTime ) );
        Input.addKeyCallback( GLFW_KEY_SPACE,         ( deltaTime ) -> jump() );// move( getUp(), deltaTime ) );
        Input.addKeyCallback( GLFW_KEY_LEFT_CONTROL,  ( deltaTime ) -> crouch() );

        //Input.addKeyCallback( GLFW_KEY_UP,    ( deltaTime ) -> rotateX( -deltaTime * rotationSensitivity ) );
        Input.addKeyCallback( GLFW_KEY_RIGHT, ( deltaTime ) -> rotateY(  deltaTime * rotationSensitivity ) );
        //Input.addKeyCallback( GLFW_KEY_DOWN,  ( deltaTime ) -> rotateX(  deltaTime * rotationSensitivity ) );
        Input.addKeyCallback( GLFW_KEY_LEFT,  ( deltaTime ) -> rotateY( -deltaTime * rotationSensitivity ) );

        addMouseCallback( MOUSE_PRIMARY, ( deltaTime ) -> {
            System.out.println("shoottingg");
        } );
        addMouseCallback( MOUSE_SECONDARY, ( deltaTime ) -> {
            System.out.println("aiminggg");
        } );
    }

    @Override
    public void simulate(float deltaTime)
    {
        super.simulate(deltaTime);
        if ( getPosition().getY() <= 0 )
        {
            setVelocity( getVelocity().setY( 0 ) );
            setPosition( getPosition().setY( 0 ) );
            isJumping = false;
        }
        else
            setVelocity( getVelocity().setY( getVelocity().getY() - GRAVITY * deltaTime ) );
    }

    private void jump()
    {
        if ( !isJumping )
        {
            Vector3f velocity = getVelocity();
            velocity.setY( velocity.getY() + JUMP_HEIGHT );
            setVelocity( velocity );
            isJumping = true;
        }
    }

    private void crouch()
    {
        if ( !isCrouching )
        {
            // ...
            isCrouching = true;
        }
    }

    public void move( Vector3f direction, float amount )
    {
        getTransform().setTranslation(
                getTransform().getTranslation().add(
                        direction.mul( amount * movingSensitivity ) ) );
    }

    private void rotateX( float angleDeg ) { getTransform().rotate( getRight(), angleDeg ); }

    private void rotateY( float angleDeg ) { getTransform().rotate( Y_AXIS, angleDeg ); }

    protected float getRotationSensitivity() { return rotationSensitivity; }
    protected float getMovingSensitivity() { return movingSensitivity; }
    protected void setRotationSensitivity( float sensitivity ) { this.rotationSensitivity = sensitivity; }

    public Vector3f getForward()  { return getTransform().getRotation().getForward(); }
    public Vector3f getBackward() { return getTransform().getRotation().getBack(); }
    public Vector3f getUp()       { return getTransform().getRotation().getUp(); }
    public Vector3f getDown()     { return getTransform().getRotation().getDown(); }
    public Vector3f getRight()    { return getTransform().getRotation().getRight(); }
    public Vector3f getLeft()     { return getTransform().getRotation().getLeft(); }
}
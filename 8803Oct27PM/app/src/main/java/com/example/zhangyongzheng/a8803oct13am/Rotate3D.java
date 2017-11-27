package com.example.zhangyongzheng.a8803oct13am;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by zhangyongzheng on 10/29/17.
 */

public class Rotate3D extends Animation {
    private final float mFromDegrees;
    private final float mToDegrees;
    //center point
    private final float mCenterX;
    private final float mCenterY;
    //depth
    private final float mDepthZ;
    //turn or not
    private final boolean mReverse;
    //test
    private Camera mCamera;

    public Rotate3D(float fromDegrees, float toDegrees, float centerX,
                    float centerY, float depthZ, boolean reverse) {
        mFromDegrees = fromDegrees;
        mToDegrees = toDegrees;
        mCenterX = centerX;
        mCenterY = centerY;
        mDepthZ = depthZ;
        mReverse = reverse;
    }

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float fromDegrees = mFromDegrees;
        // degeree in middle
        float degrees = fromDegrees
                + ((mToDegrees - fromDegrees) * interpolatedTime);
        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;
        final Matrix matrix = t.getMatrix();//get current matrix
        camera.save();
        if (mReverse) {
            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
        } else {
            camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
        }
        camera.rotateY(degrees);//翻转
        camera.getMatrix(matrix);// get tranfered matrix
        camera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }
}

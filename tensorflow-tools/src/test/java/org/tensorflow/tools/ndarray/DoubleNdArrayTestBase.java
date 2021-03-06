/*
 Copyright 2019 The TensorFlow Authors. All Rights Reserved.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 =======================================================================
 */
package org.tensorflow.tools.ndarray;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.tensorflow.tools.Shape;

public abstract class DoubleNdArrayTestBase extends NdArrayTestBase<Double> {

    @Override
    protected abstract DoubleNdArray allocate(Shape shape);

    @Override
    protected Double valueOf(Long val) {
        return val.doubleValue();
    }

    @Test
    public void iteratePrimitiveElements() {
        DoubleNdArray matrix3d = allocate(Shape.of(5, 4, 5));

        matrix3d.scalars().forEachIndexed((coords, scalar) ->
            scalar.setDouble((double)coords[2])
        );

        assertEquals(0.0, matrix3d.getDouble(0, 0, 0), 0.0);
        assertEquals(1.0, matrix3d.getDouble(0, 0, 1), 0.0);
        assertEquals(4.0, matrix3d.getDouble(0, 0, 4), 0.0);
        assertEquals(2.0, matrix3d.getDouble(0, 1, 2), 0.0);

        matrix3d.elements(1).forEach(vector ->
            vector.set(NdArrays.vectorOf(5.0, 6.0, 7.0, 8.0, 9.0))
        );

        assertEquals(5, matrix3d.getDouble(0, 0, 0), 0.0);
        assertEquals(6, matrix3d.getDouble(0, 0, 1), 0.0);
        assertEquals(9, matrix3d.getDouble(0, 0, 4), 0.0);
        assertEquals(7, matrix3d.getDouble(0, 1, 2), 0.0);
    }
}

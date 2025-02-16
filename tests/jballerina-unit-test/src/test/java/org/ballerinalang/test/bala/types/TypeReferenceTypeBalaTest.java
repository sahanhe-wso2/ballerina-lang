/*
 *  Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.ballerinalang.test.bala.types;

import org.ballerinalang.test.BCompileUtil;
import org.ballerinalang.test.BRunUtil;
import org.ballerinalang.test.CompileResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.ballerinalang.test.BAssertUtil.validateError;

/**
 * Bala tests for type reference types.
 *
 * @since 2.0.0
 */
public class TypeReferenceTypeBalaTest {

    @BeforeClass
    public void setup() {
        BCompileUtil.compileAndCacheBala("test-src/bala/test_projects/test_project_type_reference_types");
        BCompileUtil.compileAndCacheBala("test-src/bala/test_projects/test_project_type_reference_types_2");
    }

    @Test
    public void testTypeReferenceTypeViaBala() {
        CompileResult result = BCompileUtil.compile("test-src/bala/test_bala/types/type_reference_type_bala_test.bal");
        BRunUtil.invoke(result, "testFn");
    }

    @Test
    public void testTypeReferenceTypeViaBala2() {
        CompileResult result = BCompileUtil.compile(
                "test-src/bala/test_bala/types/type_reference_type_bala_test_2.bal");
        BRunUtil.invoke(result, "testFn");
    }

    @Test
    public void testTypeReferenceTypeViaBalaNegative() {
        CompileResult negativeResult = BCompileUtil.compile(
                "test-src/bala/test_bala/types/type_reference_type_bala_test_negative.bal");
        int i = 0;
        validateError(negativeResult, i++,
                "attempt to refer to non-accessible symbol 'Strings2'", 19, 32);
        validateError(negativeResult, i++, "unknown type 'Strings2'", 19, 32);
        validateError(negativeResult, i++, "unknown type 'ImmutableIntArray2'", 19, 46);
        validateError(negativeResult, i++, "attempt to refer to non-accessible symbol 'Integer2'", 20, 30);
        validateError(negativeResult, i++, "unknown type 'Integer2'", 20, 30);
        validateError(negativeResult, i++, "attempt to refer to non-accessible symbol 'Decimal2'", 20, 53);
        validateError(negativeResult, i++, "unknown type 'Decimal2'", 20, 53);
        validateError(negativeResult, i++, "unknown type 'FooBar2'", 22, 20);
        validateError(negativeResult, i++, "unknown type 'ImmutableIntArray2'", 25, 5);
        validateError(negativeResult, i++, "unknown type 'Bar2'", 29, 6);
        validateError(negativeResult, i++, "undefined field 'b' in record 'record {| |} & readonly'", 30, 9);
        validateError(negativeResult, i++,
                      "incompatible types: expected " +
                              "'(testorg/typereftypes:1.0.0:Foo|testorg/typereftypes:1.0.0:Bar)', found '()'",
                      33, 22);
        validateError(negativeResult, i++,
                      "incompatible types: expected 'testorg/typereftypes:1.0.0:Corge', found '()'",
                      34, 22);
        validateError(negativeResult, i++, "unknown type 'FooBar2'", 38, 9);
        validateError(negativeResult, i++, "unknown type 'FooBar2'", 40, 9);
        validateError(negativeResult, i++, "attempt to refer to non-accessible symbol 'IntArray2'", 43, 5);
        validateError(negativeResult, i++, "unknown type 'IntArray2'", 43, 5);
        validateError(negativeResult, i++, "attempt to refer to non-accessible symbol 'Integer2'", 44, 13);
        validateError(negativeResult, i++, "unknown type 'Integer2'", 44, 13);
        validateError(negativeResult, i++, "attempt to refer to non-accessible symbol 'Integer2'", 45, 9);
        validateError(negativeResult, i++, "unknown type 'Integer2'", 45, 9);
        validateError(negativeResult, i++, "unknown type 'ImmutableIntOrStringArray'", 49, 23);
        validateError(negativeResult, i++, "unknown type 'ImmutableIntArray2'", 49, 60);
        validateError(negativeResult, i++, "unknown type 'ImmutableIntArray2'", 50, 13);
        validateError(negativeResult, i++, "unknown type 'ImmutableIntArray2'", 51, 17);
        Assert.assertEquals(negativeResult.getErrorCount(), i);
    }
}

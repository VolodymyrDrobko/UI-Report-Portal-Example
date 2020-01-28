package core;

import com.aventstack.extentreports.Status;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import java.util.Map;

public class CustomSoftAssert extends Assertion {
    private final Map<AssertionError, IAssert<?>> errorsMap = Maps.newLinkedHashMap();
    String assertResultMessage = "";

    @Override
    public void doAssert(IAssert<?> assertionBody) {
        onBeforeAssert(assertionBody);
        try {
            assertionBody.doAssert();
            onAssertionSuccess();
        } catch (AssertionError assertionError) {
            onAssertionFail(assertionBody, assertionError);
        }
    }

    @Override
    public void onBeforeAssert(IAssert assertionBody) {
        assertResultMessage = "Verification of " + assertionBody.getMessage();
    }

    private void onAssertionSuccess() {
        TestListener.reportLogger.log(Status.PASS, assertResultMessage + " - PASSED SUCCESSFULLY");
    }

    private void onAssertionFail(IAssert assertionBody, AssertionError assertionError) {
        TestListener.reportLogger.log(Status.FAIL, assertResultMessage + " >>>>> FAIL");
        errorsMap.put(assertionError, assertionBody);
    }

    public void assertAll() {
        if (!errorsMap.isEmpty()) {
            TestListener.reportLogger.log(Status.FAIL,"Test Ended");
            System.out.println("Test Ended");
            StringBuilder sb = new StringBuilder("The following asserts failed:");
            for (Map.Entry<AssertionError, IAssert<?>> assertionErrorBody : errorsMap.entrySet()) {
                AssertionError errorBody = assertionErrorBody.getKey();
                IAssert assertionBody = assertionErrorBody.getValue();
                sb.append("\n\t");
                sb.append("Verification step - " + assertionBody.getMessage() + "\n");
                sb.append("Actual - " + assertionBody.getActual() + "\n");
                sb.append("Expected - " + assertionBody.getExpected() + "\n");
                sb.append(errorBody.getStackTrace()[6] + "\n");
            }
            errorsMap.clear();
            throw new AssertionError(sb.toString());
        }
    }
}

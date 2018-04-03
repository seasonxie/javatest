package used;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhaotang on 2017/10/18.
 */
public class type {

    public void PassRequest(Object actualValue, Object expectedValue, String fieldName_no) {
        if (expectedValue.getClass().isPrimitive() || expectedValue.getClass().isAssignableFrom(String.class)
                || expectedValue.getClass().isAssignableFrom(Boolean.class)
                || expectedValue.getClass().isAssignableFrom(Integer.class)
                || expectedValue.getClass().isAssignableFrom(Double.class)
                || expectedValue.getClass().isAssignableFrom(Float.class)
                || expectedValue.getClass().isAssignableFrom(Short.class)
                || expectedValue.getClass().isAssignableFrom(Byte.class)
                || expectedValue.getClass().isAssignableFrom(Long.class)
                || expectedValue.getClass().isAssignableFrom(Float.class)
                || expectedValue.getClass().isAssignableFrom(Date.class)) {


           // Assert.assertEquals("返回值与预期值不符：" + fieldName_no, expectedValue, actualValue);
        } else {
          //  return nextHandler.PassRequest(actualValue, expectedValue, fieldName_no);
            System.out.println();
        }
    }

    public Boolean PassRequest(Object actualValue, Object expectedValue, String fieldName_no,String[] notVerifyFlag) {
        // 如果对象属于List类型就验证
        if (expectedValue.getClass().isAssignableFrom(List.class)
                || expectedValue.getClass().isAssignableFrom(ArrayList.class)
                || expectedValue.getClass().isAssignableFrom(LinkedList.class)) {

            List<?> expectedValueList = (List<?>) expectedValue;
            List<?> actualValueList = null;
            try {
                actualValueList = (List<?>) actualValue;
            } catch (ClassCastException e) {
                e.printStackTrace();
               // Assert.assertTrue(fieldName_no + "返回值并不是List类型，而是：" + actualValue.getClass() + " 类型", false);
            }

            if (actualValue == null) {
                if (expectedValueList.size() == 0) {
                    return true;
                } else {
                 //   Assert.assertTrue(fieldName_no + " 返回值中的List为空,但是预期值不是", false);
                }

            }

           // Assert.assertEquals("输入的List:" + fieldName_no + " 的大小与返回的不等", expectedValueList.size(),
            //     actualValueList.size());

          //  VerifyHandler handler = VerifyHandlerFatory.createVerifyHandler();
            for (int i = 0; i < expectedValueList.size(); i++) {
                // 取出所有对象继续在责任链表中传递。
               // handler.PassRequest(actualValueList.get(i), expectedValueList.get(i), fieldName_no,notVerifyFlag);
            }
            return true;
        }
        // 不属于List类型,传递给下一个节点
      //  return nextHandler.PassRequest(actualValue, expectedValue, fieldName_no,info,notVerifyFlag);
        return null;
    }
}


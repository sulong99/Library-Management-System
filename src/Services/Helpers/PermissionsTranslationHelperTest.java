package Services.Helpers;

import Models.PermissionEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PermissionsTranslationHelperTest {

    @Test
    void translatePermissionAdmin() {

        PermissionEnum perrToTest = PermissionEnum.Administrator;
        String actual = PermissionsTranslationHelper.translatePermission(perrToTest.toString());

        String expected = "Administrator";

        assertEquals(expected, actual);
    }

    @Test
    void translatePermissionWorker() {

        PermissionEnum perrToTest = PermissionEnum.Worker;
        String actual = PermissionsTranslationHelper.translatePermission(perrToTest.toString());

        String expected = "Pracownik";

        assertEquals(expected, actual);
    }

    @Test
    void translatePermissionUser() {

        PermissionEnum perrToTest = PermissionEnum.User;
        String actual = PermissionsTranslationHelper.translatePermission(perrToTest.toString());

        String expected = "Czytelnik";

        assertEquals(expected, actual);
    }

    @Test
    void translatePermissionErr() {

        PermissionEnum perrToTest = PermissionEnum.User;
        String actual = PermissionsTranslationHelper.translatePermission("testtest");

        String expected = null;

        assertEquals(expected, actual);
    }

    @Test
    void translatePermissionToEnUser() {
        String txt = "Użytkownik";
        PermissionEnum actual = PermissionsTranslationHelper.translatePermissionToEn(txt);

        PermissionEnum expected = PermissionEnum.User;

        assertEquals(expected, actual);
    }

    @Test
    void translatePermissionToEnWorker() {
        String txt = "Pracownik";
        PermissionEnum actual = PermissionsTranslationHelper.translatePermissionToEn(txt);

        PermissionEnum expected = PermissionEnum.Worker;

        assertEquals(expected, actual);
    }

    @Test
    void translatePermissionToEnAdmin() {
        String txt = "Administrator";
        PermissionEnum actual = PermissionsTranslationHelper.translatePermissionToEn(txt);

        PermissionEnum expected = PermissionEnum.Administrator;

        assertEquals(expected, actual);
    }

    @Test
    void translatePermissionToEnErr() {
        String txt = "null";
        PermissionEnum actual = PermissionsTranslationHelper.translatePermissionToEn(txt);

        PermissionEnum expected = null;

        assertEquals(expected, actual);
    }
}
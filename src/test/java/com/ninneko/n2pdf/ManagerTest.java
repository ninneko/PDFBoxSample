package com.ninneko.n2pdf;

import junit.framework.TestCase;

import java.io.File;

public class ManagerTest extends TestCase {
    public void test_manager() {
        B2PdfFactory factory = B2PdfFactory.getInstance();
        B2PdfManager manager = factory.createManager();

        File file = manager.createTestPdf("aaaaaa", "testtest.pdf");

    }

}

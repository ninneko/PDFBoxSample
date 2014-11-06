package com.ninneko.n2pdf;

import java.io.File;

import junit.framework.TestCase;

public class ManagerTest extends TestCase {
    public void test_manager() {
        B2PdfManager manager = new B2PdfManager();
        File file = manager.createTestPdf("aaaaaa", "testtest.pdf");

    }
}

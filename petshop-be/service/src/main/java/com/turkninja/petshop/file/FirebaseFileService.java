package com.turkninja.petshop.file;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ali turgut bozkurt
 * Created at 8/12/2021
 */


public interface  FirebaseFileService {

    String uploadFile(InputStream is, String contentType, String path, String fileName) throws IOException;
}

package com.turkninja.petshop.file.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.turkninja.petshop.file.FirebaseFileService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ali turgut bozkurt
 * Created at 8/12/2021
 */

@Service
public class FirebaseFileServiceImpl implements FirebaseFileService {


    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/zuri-petshop.appspot.com/o/#-#-#%2F###?alt=media&token=###";
    private static final String PETSHOP_FIREBASE_PROJECT_ID = "zuri-petshop";
    private static final String PETSHOP_FIREBASE_BUCKET = "zuri-petshop.appspot.com";

    @Override
    public String uploadFile(InputStream is, String contentType, String path, String fileName) throws IOException {
        InputStream serviceAccount = getClass()
                .getClassLoader()
                .getResourceAsStream("zuri-petshop-firebase.json");
        Storage storage = StorageOptions.newBuilder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId(PETSHOP_FIREBASE_PROJECT_ID).build().getService();
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", fileName);
        BlobId blobId = BlobId.of(PETSHOP_FIREBASE_BUCKET, path + "/" + fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setMetadata(map)
                .setContentType(contentType).build();
        storage.create(blobInfo, is);
        return DOWNLOAD_URL.replaceAll("###", fileName).replaceAll("#-#-#", path);
    }
}

package com.example.worktool_new.Util;

import android.app.Activity;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import androidx.core.content.FileProvider;
import com.example.worktool_new.R;
import com.theartofdev.edmodo.cropper.CropImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ImagePickerUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static File appDir;
    static boolean compressImage = true;
    public static Activity context;
    static boolean cropImage = false;
    static File fileProfilePic = null;
    public static ImagePickerListener imagePickerListener;
    static File mFileCaptured;
    static Uri mImageCaptureUri;
    static int picHeight = 400;
    static int picWidth = 400;
    public static String tag;

    public interface ImagePickerListener {
        void onImagePicked(File file, String str);
    }

    public enum ScalingLogic {
        CROP,
        FIT
    }

    public static void pickFromCamera(Activity context2, ImagePickerListener imagePickerListener2, String tag2, boolean compressImage2) {
        tag = tag2;
        compressImage = compressImage2;
        cropImage = false;
        imagePickerListener = imagePickerListener2;
        context = context2;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + context2.getString(R.string.app_name));
                appDir = file;
                if (!file.exists()) {
                    appDir.mkdirs();
                }
                File file2 = appDir;
                mFileCaptured = new File(file2, tag2 + ".jpg");
                mImageCaptureUri = FileProvider.getUriForFile(context2, context2.getApplicationContext().getPackageName() + ".provider", mFileCaptured);
            }
            if (Build.VERSION.SDK_INT <= 21) {
                intent.setClipData(ClipData.newRawUri("", mImageCaptureUri));
                intent.addFlags(3);
            }
            intent.putExtra("output", mImageCaptureUri);
            intent.putExtra("return-data", true);
            context2.startActivityForResult(intent, 10000);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

    public static void pickFromCameraWithCrop(Activity context2, ImagePickerListener imagePickerListener2, String tag2) {
        tag = tag2;
        cropImage = true;
        imagePickerListener = imagePickerListener2;
        context = context2;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + context2.getString(R.string.app_name));
                appDir = file;
                if (!file.exists()) {
                    appDir.mkdirs();
                }
                File file2 = appDir;
                mFileCaptured = new File(file2, tag2 + ".jpg");
                mImageCaptureUri = FileProvider.getUriForFile(context2, context2.getApplicationContext().getPackageName() + ".provider", mFileCaptured);
            }
            if (Build.VERSION.SDK_INT <= 21) {
                intent.setClipData(ClipData.newRawUri("", mImageCaptureUri));
                intent.addFlags(3);
            }
            intent.putExtra("output", mImageCaptureUri);
            intent.putExtra("return-data", true);
            context2.startActivityForResult(intent, 10000);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

    public static void pickFromGallery(Activity context2, ImagePickerListener imagePickerListener2, String tag2, boolean compressImage2) {
        tag = tag2;
        compressImage = compressImage2;
        cropImage = false;
        imagePickerListener = imagePickerListener2;
        context = context2;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        context2.startActivityForResult(Intent.createChooser(intent, "Select File"), 20000);
    }

    public static void pickFromGalleryWithCrop(Activity context2, ImagePickerListener imagePickerListener2, String tag2) {
        tag = tag2;
        cropImage = true;
        imagePickerListener = imagePickerListener2;
        context = context2;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        context2.startActivityForResult(Intent.createChooser(intent, "Select File"), 20000);
    }

    public static void pickFile(Activity context2, ImagePickerListener imagePickerListener2, String tag2) {
        tag = tag2;
        imagePickerListener = imagePickerListener2;
        context = context2;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        context2.startActivityForResult(Intent.createChooser(intent, "Select File"), 30000);
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        OutputStream output;
        if (requestCode == 203) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == -1) {
                File compressToFile = compressToFile(result.getUri().getPath());
                fileProfilePic = compressToFile;
                imagePickerListener.onImagePicked(compressToFile, tag);
            } else if (resultCode == 204) {
                result.getError();
            }
        }
        if (resultCode != -1) {
            return;
        }
        if (requestCode == 10000) {
            if (cropImage) {
                CropImage.activity(mImageCaptureUri).start(context);
            } else if (compressImage) {
                File writeToFile = writeToFile(mImageCaptureUri);
                mFileCaptured = writeToFile;
                File compressToExistingFile = compressToExistingFile(writeToFile.getPath(), mFileCaptured);
                mFileCaptured = compressToExistingFile;
                imagePickerListener.onImagePicked(compressToExistingFile, tag);
            } else {
                File writeToFile2 = writeToFile(mImageCaptureUri);
                mFileCaptured = writeToFile2;
                imagePickerListener.onImagePicked(writeToFile2, tag);
            }
        } else if (requestCode == 10001) {
            imagePickerListener.onImagePicked(fileProfilePic, tag);
        } else if (requestCode == 20000) {
            if (data == null) {
                return;
            }
            if (cropImage) {
                CropImage.activity(data.getData()).start(context);
            } else if (compressImage) {
                File writeToFile3 = writeToFile(data.getData());
                fileProfilePic = writeToFile3;
                File compressToFile2 = compressToFile(writeToFile3.getPath());
                fileProfilePic = compressToFile2;
                imagePickerListener.onImagePicked(compressToFile2, tag);
            } else {
                File writeToFile4 = writeToFile(data.getData());
                fileProfilePic = writeToFile4;
                imagePickerListener.onImagePicked(writeToFile4, tag);
            }
        } else if (requestCode == 20001) {
            imagePickerListener.onImagePicked(fileProfilePic, tag);
        } else if (requestCode == 30000 && data != null) {
            try {
                Uri uri = data.getData();
                String[] filenameArray = queryName(context.getContentResolver(), uri).split("\\.");
                String fileName = tag + "." + filenameArray[filenameArray.length - 1];
                InputStream input = context.getContentResolver().openInputStream(uri);
                try {
                    File selectedFile = new File(context.getFilesDir(), fileName);
                    output = new FileOutputStream(selectedFile);
                    byte[] buffer = new byte[4096];
                    while (true) {
                        int read = input.read(buffer);
                        int read2 = read;
                        if (read != -1) {
                            output.write(buffer, 0, read2);
                        } else {
                            output.flush();
                            output.close();
                            input.close();
                            fileProfilePic = selectedFile;
                            imagePickerListener.onImagePicked(selectedFile, tag);
                            return;
                        }
                    }
                } catch (Throwable th) {
                    input.close();
                    throw th;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static File writeToFile(Uri fileUri) {
        String[] filenameArray = queryName(context.getContentResolver(), fileUri).split("\\.");
        String fileName = tag + "." + filenameArray[filenameArray.length - 1];
        try {
            InputStream input = context.getContentResolver().openInputStream(fileUri);
            File selectedFile = new File(context.getFilesDir(), fileName);
            OutputStream output = new FileOutputStream(selectedFile);
            byte[] buffer = new byte[4096];
            while (true) {
                int read = input.read(buffer);
                int read2 = read;
                if (read == -1) {
                    break;
                }
                output.write(buffer, 0, read2);
            }
            output.flush();
            output.close();
            input.close();
            if (fileName.contains(".jpg") || fileName.contains(".JPG") || fileName.contains(".jpeg") || fileName.contains(".JPEG") || fileName.contains(".png") || fileName.contains(".PNG")) {
                Bitmap bm = fixOrientation(decodeFile(selectedFile.getAbsolutePath(), 800, 800, ScalingLogic.CROP), selectedFile.getAbsolutePath());
                FileOutputStream outputStream = new FileOutputStream(selectedFile);
                bm.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
            }
            return selectedFile;
        } catch (Exception e) {
            return null;
        }
    }

    private static File compressToExistingFile(String filePath, File file) {
        try {
            Bitmap bm = fixOrientation(decodeFile(filePath, picWidth, picHeight, ScalingLogic.CROP), filePath);
            FileOutputStream outputStream = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            return file;
        } catch (Exception e) {
            return null;
        }
    }

    private static File compressToFile(String filePath) {
        try {
            Bitmap bm = fixOrientation(decodeFile(filePath, picWidth, picHeight, ScalingLogic.CROP), filePath);
            File filesDir = context.getFilesDir();
            File selectedFile = new File(filesDir, tag + ".jpg");
            FileOutputStream outputStream = new FileOutputStream(selectedFile);
            bm.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            return selectedFile;
        } catch (Exception e) {
            return null;
        }
    }

    public static Bitmap decodeFile(String path, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight, dstWidth, dstHeight, scalingLogic);
        return BitmapFactory.decodeFile(path, options);
    }

    public static int calculateSampleSize(int srcWidth, int srcHeight, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.FIT) {
            if (((float) srcWidth) / ((float) srcHeight) > ((float) dstWidth) / ((float) dstHeight)) {
                return srcWidth / dstWidth;
            }
            return srcHeight / dstHeight;
        } else if (((float) srcWidth) / ((float) srcHeight) > ((float) dstWidth) / ((float) dstHeight)) {
            return srcHeight / dstHeight;
        } else {
            return srcWidth / dstWidth;
        }
    }

    private static void deleteDirRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteDirRecursive(child);
            }
        }
        fileOrDirectory.delete();
    }

    private static String queryName(ContentResolver resolver, Uri uri) {
        if (uri.getScheme().equalsIgnoreCase("content")) {
            Cursor returnCursor = resolver.query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
            int nameIndex = returnCursor.getColumnIndex("_display_name");
            returnCursor.moveToFirst();
            String name = returnCursor.getString(nameIndex);
            returnCursor.close();
            return name;
        }
        String[] filenameArray = uri.toString().split("\\/");
        return filenameArray[filenameArray.length - 1];
    }

    public static Bitmap fixOrientation(Bitmap bm, String filePath) {
        Bitmap bitmap;
        try {
            int orientation = new ExifInterface(filePath).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 0);
            if (orientation == 1) {
                bitmap = bm;
            } else if (orientation == 3) {
                bitmap = rotateImage(bm, 180.0f);
            } else if (orientation == 6) {
                bitmap = rotateImage(bm, 90.0f);
            } else if (orientation != 8) {
                bitmap = bm;
            } else {
                bitmap = rotateImage(bm, 270.0f);
            }
            if (bitmap != null) {
                return bitmap;
            }
            return bm;
        } catch (Exception e) {
            e.printStackTrace();
            return bm;
        }
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
}

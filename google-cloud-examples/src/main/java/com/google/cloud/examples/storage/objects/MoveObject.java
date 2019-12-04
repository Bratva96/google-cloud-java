/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.examples.storage.objects;

// [START storage_move_file]
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.CopyWriter;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class MoveObject {
  public static void moveObject(
      String projectId, String sourceBucketName, String objectName, String targetBucketName) {
    // String projectId = "your-project-id";
    // String sourceBucketName = "original-object-bucket";
    // String objectName = "name-for-your-gcs-object"
    // String targetBucketName = "target-object-bucket"
    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    Blob blob = storage.get(sourceBucketName, objectName);
    // You could also use a different object name, and could the target bucket the same to rename the object
    CopyWriter copyWriter = blob.copyTo(targetBucketName, objectName);
    Blob copiedBlob = copyWriter.getResult();
    // Delete the original blob now that we've copied to where we want it, finishing the "move" operation
    blob.delete();
    System.out.println(
        "Moved object "
            + objectName
            + " from bucket "
            + sourceBucketName
            + " to "
            + copiedBlob.getBucket());
  }
}
// [END storage_move_file]
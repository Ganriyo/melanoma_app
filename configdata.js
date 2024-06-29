const { Storage } = require("@google-cloud/storage");
const { Firestore } = require("@google-cloud/firestore");
const path = require("path");
require('dotenv').config();

const projectId = process.env.PROJECTID;
const bucketId = process.env.BUCKET;

// Konfigurasi Google Cloud Storage
const storage = new Storage({
    keyFilename: path.join(__dirname, "key.json"),
    projectId: projectId,
});
const bucket = storage.bucket(bucketId);

// Konfigurasi Cloud Firestore dengan mode Native
const firestore = new Firestore({
    keyFilename: path.join(__dirname, "key.json"),
    projectId: projectId,
});
const db = firestore.collection("uploads");

module.exports = { bucket, db, Firestore };

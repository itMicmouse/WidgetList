package com.example;

import java.io.IOException;
import java.io.InputStream;

import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

public class MyClass {
    private static final ByteString PNG_HEADER = ByteString.decodeHex("89504e470d0a1a0a");

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
    }

    public void decodePng(InputStream in) throws IOException {
        BufferedSource pngSource = Okio.buffer(Okio.source(in));

        ByteString header = pngSource.readByteString(PNG_HEADER.size());
        if (!header.equals(PNG_HEADER)) {
            throw new IOException("Not a PNG.");
        }

        while (true) {
            Buffer chunk = new Buffer();

            // Each chunk is a length, type, data, and CRC offset.
            int length = pngSource.readInt();
            String type = pngSource.readUtf8(4);
            pngSource.readFully(chunk, length);
            int crc = pngSource.readInt();

            decodeChunk(type, chunk);
            if (type.equals("IEND")) break;
        }

        pngSource.close();
    }

    private void decodeChunk(String type, Buffer chunk) {
        if (type.equals("IHDR")) {
            int width = chunk.readInt();
            int height = chunk.readInt();
            System.out.printf("%08x: %s %d x %d%n", chunk.size(), type, width, height);
        } else {
            System.out.printf("%08x: %s%n", chunk.size(), type);
        }
    }
}

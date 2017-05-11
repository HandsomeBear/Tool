/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package HHH.HZIP.REF;


/**
 * <p>Factory to create Compressor[In|Out]putStreams from names. To add other
 * implementations you should extend CompressorStreamFactory and override the
 * appropriate methods (and call their implementation from super of course).</p>
 * 
 * Example (Compressing a file):
 * 
 * <pre>
 * final OutputStream out = new FileOutputStream(output); 
 * CompressorOutputStream cos = 
 *      new CompressorStreamFactory().createCompressorOutputStream(CompressorStreamFactory.BZIP2, out);
 * IOUtils.copy(new FileInputStream(input), cos);
 * cos.close();
 * </pre>
 * 
 * Example (Decompressing a file):
 * <pre>
 * final InputStream is = new FileInputStream(input); 
 * CompressorInputStream in = 
 *      new CompressorStreamFactory().createCompressorInputStream(CompressorStreamFactory.BZIP2, is);
 * IOUtils.copy(in, new FileOutputStream(output));
 * in.close();
 * </pre>
 * 
 * @Immutable
 */
public class CompressorStreamFactory {

    /**
     * Constant used to identify the BZIP2 compression algorithm.
     * @since 1.1
     */
    public static final String BZIP2 = "bzip2";

    /**
     * Constant used to identify the GZIP compression algorithm.
     * @since 1.1
     */
    public static final String GZIP = "gz";
    /**
     * Constant used to identify the PACK200 compression algorithm.
     * @since 1.3
     */
    public static final String PACK200 = "pack200";

    /**
     * Constant used to identify the XZ compression method.
     * @since 1.4
     */
    public static final String XZ = "xz";

    /**
     * Constant used to identify the LZMA compression method.
     * @since 1.6
     */
    public static final String LZMA = "lzma";

    /**
     * Constant used to identify the "framed" Snappy compression method.
     * @since 1.7
     */
    public static final String SNAPPY_FRAMED = "snappy-framed";

    /**
     * Constant used to identify the "raw" Snappy compression method.
     * @since 1.7
     */
    public static final String SNAPPY_RAW = "snappy-raw";

    /**
     * Constant used to identify the traditional Unix compress method.
     * @since 1.7
     */
    public static final String Z = "z";

    /**
     * Constant used to identify the Deflate compress method.
     * @since 1.9
     */
    public static final String DEFLATE = "deflate";

    private boolean decompressConcatenated = false;

    /**
     * Whether to decompress the full input or only the first stream
     * in formats supporting multiple concatenated input streams.
     *
     * <p>This setting applies to the gzip, bzip2 and xz formats only.</p>
     *
     * @param       decompressConcatenated
     *                          if true, decompress until the end of the
     *                          input; if false, stop after the first
     *                          stream and leave the input position to point
     *                          to the next byte after the stream
     * @since 1.5
     */
    public void setDecompressConcatenated(boolean decompressConcatenated) {
        this.decompressConcatenated = decompressConcatenated;
    }

}

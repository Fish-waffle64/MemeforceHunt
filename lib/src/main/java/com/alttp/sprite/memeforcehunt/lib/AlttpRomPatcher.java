package com.alttp.sprite.memeforcehunt.lib;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AlttpRomPatcher {
    public static final int OFFSET = 0x18A800;
    public static final int PAL_LOC = 0x103B2D;
    public static final int PAL_OW = 0x100A03;

    /*
     * Compression command:
     * recomp.exe u_item.bin item.bin 0 0 0
     *
     * for %f in (u_*.bin) do (recomp.exe %f n%f 0 0 0)
     *
     * Shoutouts to Zarby89
     */
    public static void patchROM(String romTarget, Skin s) throws IOException {
        byte[] romStream;

        try (FileInputStream fsInput = new FileInputStream(romTarget)) {
            romStream = new byte[(int) fsInput.getChannel().size()];
            fsInput.read(romStream);
            fsInput.getChannel().position(0);
            fsInput.close();

            try (FileOutputStream fsOut = new FileOutputStream(romTarget)) {
                byte[] data = s.getData();

                // clear up space (safety)
                int pos = OFFSET;
                for (int i = 0; i < 950; i++, pos++) {
                    romStream[pos] = 0;
                }

                // write graphics
                pos = OFFSET;
                for (byte b : data) {
                    romStream[pos++] = b;
                }

                romStream[PAL_LOC] = s.getPalette();
                romStream[PAL_OW] = s.getPaletteOW();

                fsOut.write(romStream, 0, romStream.length);
                fsOut.close();
            }
        }
    }
}
package com.sterndu.util;

import static com.sterndu.util.MathUtil.avg;
import java.awt.Color;

public class ColorUtil {
	public static int calculateColor(BufferedImage2 bi, int rgb, int r2, int g2, int b2, int a2) {
		return calculateColor(bi, rgb, r2, g2, b2, a2, false);
	}

	public static int calculateColor(BufferedImage2 bi, int rgb, int r2, int g2, int b2, int a2,
			boolean interpretzerovalasfirstwrite) {
		if (bi.first | (Integer) rgb == null || interpretzerovalasfirstwrite ? rgb == 0 : false) {
			return new Color(r2, g2, b2, a2).getRGB();
		} else {
			final Color o = new Color(rgb);
			final int r3 = o.getRed();
			final int g3 = o.getGreen();
			final int b3 = o.getBlue();
			final int r4 = avg(r3, r2);
			final int g4 = avg(g3, g2);
			final int b4 = avg(b3, b2);
			final int a4 = a2;
			return new Color(r4, g4, b4, a4).getRGB();
		}
	}

	public static Integer convert(int r, int g, int b) {
		return (255 & 255) << 24 | (r & 255) << 16 | (g & 255) << 8 | (b & 255) << 0;
	}

	public static int correctValue(double dv) {
		int v = (int) Math.round(dv);
		if (v > 255) {
			v = 255;
		}
		if (v < 0) {
			v = 0;
		}
		return v;
	}
}

package jp.gr.java_conf.pekokun.util;

import java.util.Objects;

public enum ByteSizeUnit {

	KB {
		@Override
		long toBytes(long size) {
			return size * UNIT_SIZE;
		}
	},
	MB {
		@Override
		long toBytes(long size) {
			return size * UNIT_SIZE * UNIT_SIZE;
		}
	},
	GB {
		@Override
		long toBytes(long size) {
			return size * UNIT_SIZE * UNIT_SIZE * UNIT_SIZE;
		}
	};

	abstract long toBytes(long size);

	static final long UNIT_SIZE = 1024;

	public static long toBytes(String size) {
		if (Objects.isNull(size) || size.isEmpty()) {
			throw new IllegalArgumentException("size must not be empty");
		}

		for (ByteSizeUnit byteSizeUnit : values()) {
			if (size.toUpperCase().endsWith(byteSizeUnit.name())) {
				return byteSizeUnit.toBytes(Long.valueOf(size.substring(0, size.length() - byteSizeUnit.name().length())));
			}
		}

		return Long.valueOf(size);
	}

}

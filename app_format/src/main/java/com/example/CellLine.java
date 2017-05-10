package com.hospital.device.printer.bluetable;

public class CellLine {
	int size;
	StringBuffer name=new StringBuffer();

	@Override
	public String toString() {
		return name!=null?name.toString():"";
	}
}

package com.sterndu.util;

import java.util.Objects;

public class D3Pos {
	double x, y, z;

	public D3Pos(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;

	}

	public double distance(D3Pos pos2) {
		return Math.sqrt(Math.pow(pos2.getX() - x, 2) + Math.pow(pos2.getY() - y, 2) + Math.pow(pos2.getZ() - z, 2));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof D3Pos))
			return false;
		D3Pos other = (D3Pos) obj;
		return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y)
				&& Double.doubleToLongBits(z) == Double.doubleToLongBits(other.z);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "D3Pos [x=" + x + ", y=" + y + ", z=" + z + ", hashCode()=" + hashCode() + ", getClass()="
				+ this.getClass() + "]";
	}

}

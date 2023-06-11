package com.sterndu.util;

import java.util.Objects;

public class D2Pos {
	double x, y;

	public D2Pos(double x, double y) {
		this.x = x;
		this.y = y;

	}

	public double distance(D2Pos pos2) {
		return Math.sqrt(Math.pow(pos2.getX() - x, 2) + Math.pow(pos2.getY() - y, 2));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof D2Pos))
			return false;
		D2Pos other = (D2Pos) obj;
		return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "(x=" + x + "|y=" + y + ")";
	}

}

package com.sterndu.util.vector;

public class Vec2f {
	public float x, y;

	public Vec2f() {
		x=0.0f;
		y=0.0f;
	}

	public Vec2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vec2f(Vec2f vector) {
		x = vector.x;
		y = vector.y;
	}

	public float distance(Vec2f vector) {
		float dist = 0, xdist = x - vector.x, ydist = y - vector.y;
		if (xdist < 0) xdist *= -1;
		if (ydist < 0) ydist *= -1;
		dist = (float) Math.sqrt(Math.pow(xdist, 2) + Math.pow(ydist, 2));
		return dist;
	}
}

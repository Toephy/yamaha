package com.lixing.myJunit.bean;

import com.google.common.base.Objects;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Fruit {
	
	private String name;
	private int count;
	private String color;
	
	public Fruit(String name, int count) {
		this.name = name;
		this.count = count;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("count", count)
                .append("color", color)
                .toString();
    }

    public String toString2() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("count", count)
                .add("color", color)
                .toString();
    }

    public String toString3() {
        final StringBuilder sb = new StringBuilder("Fruit{");
        sb.append("name='").append(name).append('\'');
        sb.append(", count=").append(count);
        sb.append(", color='").append(color).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        Fruit fruit = new Fruit("a", 1);
        System.out.println(fruit);
        System.out.println(fruit.toString2());
        System.out.println(fruit.toString3());
    }
}

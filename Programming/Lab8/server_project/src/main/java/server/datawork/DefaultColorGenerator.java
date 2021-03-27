package server.datawork;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class DefaultColorGenerator implements ColorGenerator{

    private final Set<Color> colorSet;

    public DefaultColorGenerator() {
        colorSet = new HashSet<>();
    }

    @Override
    public Color generateColor() {
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        Color color = new Color(red, green, blue);
        if(colorSet.contains(color))
            return generateColor();
        else
            return color;
    }

    @Override
    public void addColor(Color color) {
        colorSet.add(color);
    }

    @Override
    public void freeColor(Color color) {
        colorSet.remove(color);
    }
}

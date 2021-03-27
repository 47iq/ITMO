package server.datawork;

import java.awt.*;

public interface ColorGenerator {
    Color generateColor();
    void addColor(Color color);
    void freeColor(Color color);
}

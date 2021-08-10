/*
 * Copyright (C) 2021 Hiram K. <https://github.com/IdelsTak>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.idelstak.arrayblockingqueue.screensaver;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Hiram K. <https://github.com/IdelsTak>
 */
public class Producer extends Task<Void> {

    private final Scene scene;

    private final Queue<Shape> shapes;
    private final int capacity;

    public Producer(Scene scene, Queue<Shape> shapes, int capacity) {
        this.scene = scene;
        this.shapes = shapes;
        this.capacity = capacity;
    }

    @Override
    protected Void call() throws Exception {
        //Create rectangles endlessly
        while (true) {
            if (shapes.isEmpty()) {
                var random = ThreadLocalRandom.current();

                for (int i = 0; i < capacity; i++) {
                    var xCoordinate = random.nextInt(Double.valueOf(scene.getWidth()).intValue());
                    var yCoordinate = random.nextInt(Double.valueOf(scene.getHeight()).intValue());
                    var width = random.nextInt(20);
                    var height = random.nextInt(50);
                    var shape = new Rectangle(xCoordinate, yCoordinate, width, height);

                    shape.setArcWidth(20);
                    shape.setArcHeight(20);

                    var hue = random.nextDouble() * 360.0;
                    var saturation = random.nextDouble();
                    var brightness = random.nextDouble();
                    var opacity = random.nextDouble();
                    shape.setFill(Color.hsb(hue, saturation, brightness, opacity));

                    shapes.offer(shape);
                }
            } else {
                //Simulate a pause before creating and adding a
                //new batch of rectangles to the queue
                Thread.sleep(1000L);
            }
        }
    }

}

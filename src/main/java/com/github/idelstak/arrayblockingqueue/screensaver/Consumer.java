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
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.shape.Shape;

/**
 *
 * @author Hiram K. <https://github.com/IdelsTak>
 */
public class Consumer extends Task<Void> {

    private final Group root;
    private final Queue<Shape> shapes;

    public Consumer(Group root, Queue<Shape> shapes) {
        this.root = root;
        this.shapes = shapes;
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            for (var i = 0; i < shapes.size(); i++) {
                Platform.runLater(() -> {
                    var shape = shapes.poll();
                    if (shape != null) {
                        root.getChildren().add(shape);
                    }
                });
            }
        }
    }

}

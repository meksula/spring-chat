package com.bluemangoose.client.controller.loader;

import javafx.event.Event;
import javafx.scene.Node;

/**
 * @Author
 * Karol Meksuła
 * 18-07-2018
 * */

public interface FxmlLoader {
    void loadNewStage(final String PATH);

    void loadSameStage(final String PATH, final Event event);

    void loadSameStage(final String PATH, final Node node);

    void loadNewStageWithData(final FxmlLoaderTemplate.SceneType sceneType, final Object DATA);

    void loadSameStageWithData(final FxmlLoaderTemplate.SceneType sceneType, final Object DATA, final Event event);

    void loadSameStageWithData(final FxmlLoaderTemplate.SceneType sceneType, final Object DATA, final Node node);
}

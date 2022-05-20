package com.github.onsdigital.dp.interactives.api;

import com.github.onsdigital.dp.interactives.api.models.Interactive;

public interface Client {

    /**
     * Publishes interactives in the Interactives Service that are associated with the provided collectionId.
     *
     * @param collectionId
     */
    void publishCollection(String collectionId);

    /**
     * Get all interactives part of a collection
     *
     * @param collectionId
     */
    Interactive[] getInteractivesInCollection(String collectionId);

    /**
     * Get interactive (given the id)
     *
     * @param interactiveId
     */
    Interactive getInteractive(String interactiveId);
}

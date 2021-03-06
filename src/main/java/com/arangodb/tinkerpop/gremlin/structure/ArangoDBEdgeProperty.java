//////////////////////////////////////////////////////////////////////////////////////////
//
// Implementation of the TinkerPop-Enabled Providers OLTP for ArangoDB
//
// Copyright triAGENS GmbH Cologne and The University of York
//
//////////////////////////////////////////////////////////////////////////////////////////

package com.arangodb.tinkerpop.gremlin.structure;

import java.util.Collections;

import org.apache.tinkerpop.gremlin.structure.Direction;
import org.apache.tinkerpop.gremlin.structure.Element;

import com.arangodb.ArangoCursor;
import com.arangodb.tinkerpop.gremlin.client.ArangoDBBaseDocument;
import com.arangodb.tinkerpop.gremlin.client.ArangoDBIterator;
import com.arangodb.tinkerpop.gremlin.client.ArangoDBPropertyFilter;
import com.arangodb.tinkerpop.gremlin.utils.ArangoDBUtil;

/**
 * The Class ArangoDBEdgeProperty.
 *
 * @param <V> the generic type
 * 
 * @author Horacio Hoyos Rodriguez (@horaciohoyosr)
 */
public class ArangoDBEdgeProperty<V> extends ArangoDBElementProperty<V> {

    /**
     * Constructor used for Arabgo DB JavaBeans serialisation.
     */

    public ArangoDBEdgeProperty() {
        super();
    }

    /**
     * Instantiates a new Arango DB edge property.
     *
     * @param key the key
     * @param value the value
     * @param owner the owner
     */
    
    public ArangoDBEdgeProperty(String key, V value, ArangoDBBaseDocument owner) {
        super(key, value, owner, ArangoDBUtil.ELEMENT_PROPERTIES_COLLECTION);
    }
    
    @Override
    public Element element() {
        ArangoCursor<ArangoDBEdge> q = graph.getClient().getDocumentNeighbors(graph.name(), this, Collections.emptyList(), Direction.IN, ArangoDBPropertyFilter.empty(), ArangoDBEdge.class);
		ArangoDBIterator<ArangoDBEdge> iterator = new ArangoDBIterator<ArangoDBEdge>(graph, q);
        return iterator.hasNext() ? iterator.next() : null;
    }
}

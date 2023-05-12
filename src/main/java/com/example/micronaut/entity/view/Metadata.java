package com.example.micronaut.entity.view;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Metadata(
        String etag,
        String asof ) {
}

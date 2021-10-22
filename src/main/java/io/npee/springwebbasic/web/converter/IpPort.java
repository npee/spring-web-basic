package io.npee.springwebbasic.web.converter;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class IpPort {
    private String ip;
    private int port;
}
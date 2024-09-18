package com.academy.ui.components.sub;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class ButtonState {
    private String buttonColor;
    private Dimension buttonSize;
    private String text;
    private String textColor;
    private Point location;
}

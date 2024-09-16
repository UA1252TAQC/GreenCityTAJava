package com.academy.ui.components.sub;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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

package edu.columbia.cs.psl.halo.client.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

class Thermometer extends Canvas {
	private double minimum;
	private double maximum;
	private double value;
	private Point textSize;
	private String text;

	private Color colorBlack;
	private Color colorWhite;
	private Color colorNormalShadow;
	private Color colorHighlightShadow;
	private Color colorText;
	private Color colorBarSet;
	private Color colorGrey;

	/**
	 * create progress bar
	 * 
	 * @param composite
	 *            parent composite widget
	 * @param style
	 *            style flags
	 */
	Thermometer(Composite composite, int style) {
		super(composite, SWT.NONE);

		this.colorBlack = getDisplay().getSystemColor(SWT.COLOR_BLACK);
		this.colorWhite = getDisplay().getSystemColor(SWT.COLOR_WHITE);
		this.colorNormalShadow = getDisplay().getSystemColor(
				SWT.COLOR_WIDGET_NORMAL_SHADOW);
		this.colorHighlightShadow = getDisplay().getSystemColor(
				SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW);
		this.colorText = new Color(null, 0xDD, 0xDD, 0xDD);
		this.colorBarSet = new Color(null, 0x00, 0x44, 0x9C);
		this.colorGrey = composite.getBackground();
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent disposeEvent) {
				Thermometer.this.widgetDisposed(disposeEvent);
			}
		});
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent paintEvent) {
				Thermometer.this.paint(paintEvent);
			}
		});

		setSelection(0.0, "");
	}

	/**
	 * compute size of pane
	 * 
	 * @param wHint
	 *            ,hHint width/height hint
	 * @param changed
	 *            TRUE iff no cache values should be used
	 * @return size
	 */
	public Point computeSize(int wHint, int hHint, boolean changed) {
		GC gc;
		int width, height;

		if (wHint == SWT.DEFAULT)
			wHint = 180;
		width = 0;
		height = 0;

		width = 2 + textSize.x + 2;
		height = 2 + textSize.y + 2;
		if (wHint != SWT.DEFAULT)
			width = wHint;
		if (hHint != SWT.DEFAULT)
			height = hHint;

		return new Point(width, height);
	}

	/**
	 * set minimal progress value
	 * 
	 * @param n
	 *            value
	 */
	public void setMinimum(double n) {
		this.minimum = n;
	}

	/**
	 * set maximal progress value
	 * 
	 * @param n
	 *            value
	 */
	public void setMaximum(double n) {
		this.maximum = n;
	}

	public double getValue() {
		return value;
	}

	/**
	 * set progress value
	 * 
	 * @param n
	 *            value
	 */
	public void setSelection(double n, String s) {
		GC gc;

		value = Math.min(
				Math.max(((maximum - minimum) > 0.0) ? (n - minimum)
						/ (maximum - minimum) : 0.0, minimum), maximum);

		gc = new GC(this);
		text = s;
		textSize = gc.stringExtent(s);
		gc.dispose();

		redraw();
	}

	/**
	 * free allocated resources
	 * 
	 * @param disposeEvent
	 *            dispose event
	 */
	private void widgetDisposed(DisposeEvent disposeEvent) {
		colorBarSet.dispose();
		colorText.dispose();
	}

	int numSegments = 4;

	public void setNumSegments(int numSegments) {
		this.numSegments = numSegments;
	}

	/**
	 * paint progress bar
	 * 
	 * @param paintEvent
	 *            paint event
	 */
	private void paint(PaintEvent paintEvent) {
		GC gc;
		Rectangle bounds;
		int x, y, w, h;

		gc = paintEvent.gc;
		bounds = getBounds();
		x = 0;
		y = 0;
		w = bounds.width;
		h = bounds.height;

		// shadow
		gc.setForeground(colorNormalShadow);
		gc.drawRectangle(x + 0, y + 0, w - 2, h - 2);

		gc.setForeground(colorHighlightShadow);
		gc.drawLine(x + 1, y + 1, x + w - 3, y + 1);
		gc.drawLine(x + 1, y + 2, x + 1, y + h - 3);
		gc.drawLine(x + 0, y + h - 1, x + w - 1, y + h - 1);
		gc.drawLine(x + w - 1, y + 0, x + w - 1, y + h - 2);

		// draw bar
		gc.setBackground(colorGrey);
		gc.fillRectangle(x + 2, y + 2, w - 4, h - 4);
		gc.setBackground(colorBarSet);
		gc.fillRectangle(x + 2, y + 2, (int) ((double) (w - 4) * value), h - 4);

		// int space = w/numSegments;
		for (int i = 0; i < w; i += 20) {
			gc.setBackground(colorGrey);
			gc.fillRectangle(x + i, y + 2, 2, h - 4);
		}
		// draw percentage text
		gc.setForeground(colorBlack);

		// gc.drawString(text,(w-textSize.x)/2,(h-textSize.y)/2,true);
		gc.setBackground(colorWhite);
		gc.setAlpha(200);
		gc.drawText(text, (w - textSize.x) / 2, (h - textSize.y) / 2, false);
	}
}

/* end of file */

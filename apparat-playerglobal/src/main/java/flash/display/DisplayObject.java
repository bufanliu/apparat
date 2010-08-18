package flash.display;

import flash.events.EventDispatcher;
import jitb.display.DisplayList;
import jitb.display.IDisplayObject;
import org.lwjgl.opengl.GL11;

/**
 * @author Joa Ebert
 */
public abstract class DisplayObject extends EventDispatcher implements IBitmapDrawable, IDisplayObject {
	private double _x = 0.0;
	private double _y = 0.0;
	private double _rotation = 0.0;
	private String _blendMode = BlendMode.NORMAL;

	public DisplayObject() {
		DisplayList.register(this);
	}
	
	public Stage stage() { return null; }

	public String blendMode() { return _blendMode; }
	public void blendMode(final String value) { _blendMode = value; }

	public double x() { return _x; }
	public void x(final double value) { _x = value; }

	public double y() { return _y; }
	public void y(final double value) { _y = value; }

	public double rotation() { return _rotation; }
	public void rotation(final double value) { _rotation = value; }

	protected abstract void JITB$render();

	public final void JITB$renderDisplayObject() {
		GL11.glPushMatrix();
		GL11.glRotatef((float)(_rotation / 180.0 * Math.PI), 0.0f, 0.0f, 1.0f);
		GL11.glTranslated(x(), y(), 0.0);

		final String blendMode = blendMode();
		boolean disableDepthTest = false;

		if(blendMode.equals(BlendMode.ADD)) {
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			disableDepthTest = true;
		}

		try {
			JITB$render();
		} finally {
			if(disableDepthTest) {
				GL11.glDisable(GL11.GL_BLEND);
			}
			GL11.glPopMatrix();
		}
	}
}

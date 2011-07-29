package edu.columbia.cs.psl.halo.server.wrapper;


public class EqualsHashCodeProvider {
	@Override
	public boolean equals(Object obj) {
		if(getClass().equals(obj.getClass()))
		{
			try {
				return obj.getClass().getMethod("getId").invoke(obj).equals(this.getClass().getMethod("getId").invoke(this));
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
		}
		return false;
	}
	@Override
	public int hashCode() {
		try {
			int result = ((Integer) this.getClass().getMethod("getId").invoke(this))* 29;
			result = 29 * result + getClass().getName().hashCode();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return super.hashCode();
		}
	}
}

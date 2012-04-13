/*
 * Jinx is Copyright 2010-2012 by Jeremy Brooks and Contributors
 *
 * This file is part of Jinx.
 *
 * Jinx is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jinx is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Jinx.  If not, see <http://www.gnu.org/licenses/>.
*/
package net.jeremybrooks.jinx.dto;

/**
 * <pair namespace="aero" predicate="airline" usage="1093">aero:airline</pair>
 * @author emeraldjava
 *
 */
public class Pair {

	private String namespace;
	private int usage;
	private String predicates;
	private String value;
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public int getUsage() {
		return usage;
	}
	public void setUsage(int usage) {
		this.usage = usage;
	}
	public String getPredicates() {
		return predicates;
	}
	public void setPredicates(String predicates) {
		this.predicates = predicates;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pair[usage=");
		builder.append(usage);
		builder.append(", predicates=");
		builder.append(predicates);
		builder.append(", namespace=");
		builder.append(namespace);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}

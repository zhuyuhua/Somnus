/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bj58.sfft.json.jsontools.helper;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * TODO
 *
 * @author zhuyuhua
 * @since 0.0.1
 */
public class HelperRepository<T extends Helper> {

	private HelperTreeNode<T> root;

	public HelperRepository() {
		this.root = new HelperTreeNode(new RootHelper(null));
	}

	public void addHelper(T aHelper) {
		this.root.insertNode(new HelperTreeNode(aHelper));
	}

	public T findHelper(Class aClass) {
		return this.root.findHelper(aClass);
	}

	public String prettyPrint() {
		return this.root.prettyPrint("");
	}

	private static class HelperTreeNode<T extends Helper> {
		private T helper;
		private List<HelperTreeNode<T>> children;

		public HelperTreeNode(T aClass) {
			this.helper = aClass;
			this.children = new LinkedList();
		}

		public T getHelper() {
			return this.helper;
		}

		public boolean insertNode(HelperTreeNode<T> aNode) {
			if (aNode.getHelper().getHelpedClass() == this.helper.getHelpedClass()) {
				this.helper = aNode.getHelper();
				return true;
			}
			if (this.helper.getHelpedClass().isAssignableFrom(aNode.getHelper().getHelpedClass())) {
				boolean insertedToSomeChild = false;
				for (HelperTreeNode lChildren : this.children) {
					boolean lSuccess = lChildren.insertNode(aNode);
					if (lSuccess) {
						insertedToSomeChild = true;
						break;
					}

				}

				if (!insertedToSomeChild) {
					Iterator lIter2 = this.children.iterator();
					while (lIter2.hasNext()) {
						HelperTreeNode lChild = (HelperTreeNode) lIter2.next();
						if (aNode.getHelper().getHelpedClass().isAssignableFrom(lChild.getHelper().getHelpedClass())) {
							lIter2.remove();
							aNode.insertNode(lChild);
						}

					}

					this.children.add(aNode);
				}
				return true;
			}

			return false;
		}

		T findHelper(Class aClass) {
			if (this.helper.getHelpedClass() == aClass) {
				return this.helper;
			}

			for (HelperTreeNode lChildNode : this.children) {
				Helper lHelper = lChildNode.findHelper(aClass);
				if (lHelper != null) {
					return (T) lHelper;
				}

			}

			if (this.helper.getHelpedClass().isAssignableFrom(aClass)) {
				return this.helper;
			}
			return null;
		}

		public String prettyPrint(String aIndent) {
			StringBuilder lBld = new StringBuilder(aIndent);
			lBld.append(this.helper.getHelpedClass().getName());
			for (HelperTreeNode lChild : this.children) {
				lBld.append("\n");
				lBld.append(lChild.prettyPrint(aIndent + "   "));
			}
			return lBld.toString();
		}
	}

	private static class RootHelper implements Helper {

		public RootHelper(Object object) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public Class getHelpedClass() {
			return Object.class;
		}
	}
}